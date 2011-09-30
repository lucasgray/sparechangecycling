package com.sparechangecycling.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.CharUtils;

import com.sparechangecycling.dao.CraigsDAO;
import com.sparechangecycling.pojos.ZipCode;
import com.sun.syndication.feed.synd.SyndEntry;

public class EntryScraper {
	
	private static final int ZIP_RADIUS = 30;
	private CraigsDAO dao;
	private ZipCode defaultZip;
	private static final float DEFAULT_PRICE = 0f;
	private Map<String, String> cities;
	private SyndEntry entry;
	
	public EntryScraper(CraigsDAO dao) {
		this.dao = dao;
	}
	
	public void setCommunity(String community) {
		cities = new HashMap<String, String>();
		defaultZip = dao.getDefaultZipCode(community);
		cities.putAll(dao.getNearbyCities(defaultZip.getZip(), ZIP_RADIUS));
	}
	
	public void setEntry(SyndEntry entry) {
		this.entry = entry;
	}

	public String getTitle() {
		String scraped = HtmlScraper.scrape(entry.getTitle());
		int a = scraped.lastIndexOf("(");
		if(a != -1) {
			return scraped.substring(0, a);
		}
		return scraped;
	}
	
	public boolean include() {
		//logic to remove 'wanted' posts.
		return true;
	}

	public float getPrice() {
		try {
			final String title = entry.getTitle();
			//it is free!
			if(title.toUpperCase().contains("FREE")) {
				return DEFAULT_PRICE;
			}
			int a = title.lastIndexOf("$");
			//it has a price (in dollars)
			if(a != -1) {
				char[] chars = title.toCharArray();
				StringBuilder sb = new StringBuilder();
				while(++a < chars.length && CharUtils.isAsciiNumeric(chars[a])) {
					sb.append(chars[a]);
				}
				return Float.valueOf(sb.toString());
			}
			//no price given
			return DEFAULT_PRICE;
		} catch (NumberFormatException e) {
			return DEFAULT_PRICE;
		}
	}

	public ZipCode getZip() {
		String location = getLocation();
		if(location==null) {
			return defaultZip;
		}
		String matched = getFromCities(location);
		if(matched == null) {
			return defaultZip;
		}
		//we have a match! 
		return dao.getZip(matched, cities.get(matched));
		//TODO: if slash, then two locations.. find the zip code of central location?
		//TODO: if location is abbreviation.. ("CR" for cedar rapids), match with set of cities
		//TODO: replace abbreviations in cities ("Mt." -> mount, "St." -> saint)
		//TODO: still the problem that if someone puts Madison, WI, which zip code for madison
		//do we give it?
		
	}

	public long getId() {
		String link = entry.getLink();
		return Long.valueOf(link.substring(1 + link.lastIndexOf("/"), link.lastIndexOf(".")));
	}

	public String getLocation() {
		String scraped = HtmlScraper.scrape(entry.getTitle());
		int a = scraped.lastIndexOf("(");
		int b = scraped.lastIndexOf(")");
		if(a != -1 && b!= -1 && a < b) {
			return scraped.substring(a+1, b);
		}
		return null;
	}
	
	private String getFromCities(String location) {
		for (String city : cities.keySet()) {
			if(location.toUpperCase().contains(city)) {
				return city;
			}
		}
		return null;
	}


}
