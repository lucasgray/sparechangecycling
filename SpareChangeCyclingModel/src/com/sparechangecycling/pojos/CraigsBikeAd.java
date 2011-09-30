package com.sparechangecycling.pojos;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.sparechangecycling.util.CraigsAdProbabilities;

@javax.persistence.Entity
@Table(name = "craigsAds")

public class CraigsBikeAd implements BikeAd,Comparable<CraigsBikeAd> {

	@Id
	@GeneratedValue
	private Long id;
	private BigDecimal price;
	private String[] images;
	private String title;
	@Column(length=5000)
	private String detail;
	private Date date;
	private String type;
	private String location;
	
	private String clSubdomain;
	
	private URL link;
	@Column(name="craigs_id",unique=true)
	private Long craigsId;
	
	@OneToOne(targetEntity=ZipCode.class,fetch=FetchType.LAZY)
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="zip")
	private ZipCode zip;
	
	public CraigsBikeAd(String title, String detail) {
		this.title = title;
		this.detail = detail;
		this.type = CraigsAdProbabilities.guessType(title,detail);
	}
	
	public CraigsBikeAd() {
		super();
	}

	public Long getCraigsId() {
		return craigsId;
	}

	public void setCraigsId(Long craigsId) {
		this.craigsId = craigsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public URL getLink() {
		return link;
	}

	public void setLink(URL link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof CraigsBikeAd) {
			CraigsBikeAd ad = (CraigsBikeAd) obj;
			return this.getTitle().equals(ad.getTitle())
					&& this.getDate().equals(ad.getDate());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 13 * this.getDate().hashCode() * this.getTitle().hashCode()
				* this.getDetail().hashCode();
	}

	@Override
	public String toString() {
		return StringUtils.join(Arrays.asList(detail, title, type, date, images, link, price), " : "); 
	}

	public int compareTo(CraigsBikeAd o) {
		return this.getDate().compareTo(o.getDate());
	}


	public String getClSubdomain() {
		return clSubdomain;
	}

	public void setClSubdomain(String clSubdomain) {
		this.clSubdomain = clSubdomain;
	}

	public ZipCode getZip() {
		return zip;
	}

	public void setZip(ZipCode zip) {
		this.zip = zip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
