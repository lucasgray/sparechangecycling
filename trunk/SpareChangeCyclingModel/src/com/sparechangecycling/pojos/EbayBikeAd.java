package com.sparechangecycling.pojos;

import java.net.URL;
import java.util.Date;

public class EbayBikeAd implements BikeAd,java.lang.Comparable<EbayBikeAd>{

	private URL link;
	private URL picLink;
	private String currentPrice;
	private String buyItNowPrice;
	private String title;
	private Date endDateTime;
	public URL getLink() {
		return link;
	}
	public void setLink(URL link) {
		this.link = link;
	}
	public String getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getBuyItNowPrice() {
		return buyItNowPrice;
	}
	public void setBuyItNowPrice(String buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public int compareTo(EbayBikeAd arg0) {
		//reverse it
		return this.endDateTime.compareTo(arg0.endDateTime);
	}
	public URL getPicLink() {
		return picLink;
	}
	public void setPicLink(URL picLink) {
		this.picLink = picLink;
	}
	
	
	
	
}
