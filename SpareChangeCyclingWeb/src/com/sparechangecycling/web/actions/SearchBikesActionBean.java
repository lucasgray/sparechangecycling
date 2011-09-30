package com.sparechangecycling.web.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.sparechangecycling.daos.BikesDAO;
import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.CraigsBikeAd;
import com.sparechangecycling.pojos.EbayBikeAd;
import com.sparechangecycling.pojos.SccBikeAd;
import com.sparechangecycling.proxy.EbayProxy;
import com.sparechangecycling.util.SearchPreferences;

@UrlBinding("/used-bikes/buy-bikes/{$event}")
public class SearchBikesActionBean extends BaseActionBean {

	private List<EbayBikeAd> ebayBikes;
	private List<CraigsBikeAd> craigsBikes;
	private List<SccBikeAd> sccBikes;
	
	private String tab;
	
	private BikesDAO dao;
	private EbayProxy proxy;
	
	private Logger logger = LoggerFactory.getLogger(SearchBikesActionBean.class);
	
	public SearchBikesActionBean() throws ParseException, FailingHttpStatusCodeException, IOException, SQLException {
		
		proxy = new EbayProxy();
		dao = new BikesDAO();
	}
	
	@DefaultHandler
	@HandlesEvent("search") 
	public Resolution begin(){
		//getContext().getRequest().setAttribute("theTab", tab);
		return new ForwardResolution("/buy.jsp");
	}

	public List<SccBikeAd> getLatestAds() throws SQLException, ParseException {
		SearchPreferences sp = new SearchPreferences();
		List<AdType> types = new ArrayList<AdType>();
		types.add(AdType.ALL);
		sp.setType(types);
		sp.setDate(new Date());
		return dao.getLatestSccForIndex(sp);
	}

	public List<SccBikeAd> getSccBikes() throws SQLException, ParseException {	
		
		sccBikes=dao.getSccBikes(getContext().getSearchPreferences());

		return sccBikes;
	}

	public List<CraigsBikeAd> getCraigsBikes() throws ParseException, SQLException {
		
		craigsBikes = dao.getCraigsBikes(getContext().getSearchPreferences());
		
		return craigsBikes;
	}
	
	public List<EbayBikeAd> getEbayBikes() throws FailingHttpStatusCodeException, IOException, ParseException {
		
		ebayBikes=proxy.getEbayAds(getContext().getSearchPreferences());
		
		return ebayBikes;
	}

	public void setEbayBikes(List<EbayBikeAd> ebayBikes) {
		this.ebayBikes = ebayBikes;
	}

	public void setCraigsBikes(List<CraigsBikeAd> craigsBikes) {
		this.craigsBikes = craigsBikes;
	}

	public void setSccBikes(List<SccBikeAd> sccBikes) {
		this.sccBikes = sccBikes;
	}
	
    public void setSearchPreferences(SearchPreferences pref) {
    	//logger.info(pref.getType().toString());
    	this.getContext().setSearchPreferences(pref);
    }
    public SearchPreferences getSearchPreferences(){
    	return this.getContext().getSearchPreferences();
    }
    
	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}
}
