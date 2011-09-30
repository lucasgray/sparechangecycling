package com.sparechangecycling.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.pojos.AdType;

public class SearchPreferences {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Date date;
	private List<AdType> type;
	private String zip;
	private Integer distance;
	private Integer lowPrice;
	private Integer highPrice;
	private String textMatch;
	
	public SearchPreferences() {super();}
	
	public SearchPreferences(Date date, List<AdType> type, String zip,
			Integer distance,Integer highPrice,Integer lowPrice,String textMatch) {
		super();
		this.date = date;
		this.type = type;
		this.zip = zip;
		this.distance = distance;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.textMatch = textMatch;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<AdType> getType() {
		return type;
	}
	public void setType(List<AdType> type) {
		log.debug("setting types"+type.toString());
		this.type = type;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Integer getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Integer lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Integer getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Integer highPrice) {
		this.highPrice = highPrice;
	}
	public String getTextMatch() {
		return textMatch;
	}
	public void setTextMatch(String textMatch) {
		this.textMatch = textMatch;
	}
	
}
