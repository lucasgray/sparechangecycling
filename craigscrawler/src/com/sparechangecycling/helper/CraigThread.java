package com.sparechangecycling.helper;

import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.dao.CraigsDAO;
import com.sparechangecycling.hibernate.HibernateSessionFactory;
import com.sparechangecycling.pojos.CraigsBikeAd;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class CraigThread extends Thread {

	private static final String RSS_FEED = "http://%s.craigslist.org/bik/index.rss";
	private static final Logger logger = LoggerFactory.getLogger(CraigThread.class);
	
	private Session session;
	private EntryScraper es;
	private CraigsDAO dao;
	private String commie;
	private int numAdded = 0;
	private FeedTimer timer;
	
	public CraigThread(CraigsDAO dao, String commie, FeedTimer timer) {
		this.dao = dao;
		this.commie = commie;
		this.timer = timer;
		session = HibernateSessionFactory.openSession();
		es = new EntryScraper(dao);
	}
	
	@Override
	public synchronized void run() {
		Transaction tx = null;
		try {
			es.setCommunity(commie);
			long lastId = dao.getLastEntry(commie);
			String urlOfCity = String.format(RSS_FEED, commie);
			
			URL city = new URL(urlOfCity);
			
			URLConnection urlconn = city.openConnection();
			urlconn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
			
			XmlReader xmlReader = new XmlReader(urlconn);
			SyndFeed feed = new SyndFeedInput().build(new XMLFilterReader(xmlReader));
			for (Object entryObj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry)entryObj;
				es.setEntry(entry);
				long id = es.getId();
				if(lastId >= id) {
					break;		
				}
				String detail = HtmlScraper.scrape(entry.getDescription().getValue());
				String title = es.getTitle();
				CraigsBikeAd ad = new CraigsBikeAd(title, detail);
				Date publishedDate = entry.getPublishedDate();
				if(publishedDate == null) {
					publishedDate = new Date();
				}
				ad.setDate(publishedDate);
				ad.setLink(new URL(entry.getLink()));
				ad.setCraigsId(id);
				ad.setClSubdomain(commie);
				ad.setPrice(new BigDecimal(es.getPrice()));
				ad.setZip(es.getZip());
				ad.setLocation(es.getLocation());
				try {
					tx = session.beginTransaction();
					session.save(ad);
					tx.commit();
					numAdded++;
				} catch(ConstraintViolationException e) {
					session = handleDuplicate(commie, tx, e);
				}
			}
			session.flush();
		} catch (Exception e) {
			logger.warn(commie + " failed!", e);
		} finally {
			session.close();
		}
		timer.updateRate(numAdded);
	}

	private Session handleDuplicate(String commie, Transaction tx,
			ConstraintViolationException e) {
		Session session;
		Matcher m = Pattern.compile("[0-9]+").matcher(e.getCause().getMessage());
		if(m.find()) {
			logger.info("Entry already found for id " + m.group(0) + " in community " + commie);
		} else {
			e.printStackTrace();
		}
		if(tx != null && tx.isActive()) {
			tx.rollback();
		}
		session = HibernateSessionFactory.openSession();
		return session;
	}
	
}
