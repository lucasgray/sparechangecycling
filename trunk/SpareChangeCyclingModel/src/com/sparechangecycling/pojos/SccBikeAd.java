package com.sparechangecycling.pojos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name="scc_ads")
public class SccBikeAd implements BikeAd{

	@Id
	@GeneratedValue
	private Long id;
	
	private String type;
	private String firstTitle;
	private String secondTitle;
	@Column(length=5000)
	private String description;
	private BigDecimal price;
	private String year;
	private Date date;
	@OneToMany(targetEntity=Pic.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Pic> pics;
	@OneToMany(targetEntity=Thumb.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Thumb> thumbs;
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	@OneToOne(targetEntity=ZipCode.class,fetch=FetchType.LAZY)
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="zip")
	private ZipCode zip;
	
	public SccBikeAd() {
		super();
		pics = new ArrayList<Pic>();
		thumbs = new ArrayList<Thumb>();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFirstTitle() {
		return firstTitle;
	}
	public void setFirstTitle(String firstTitle) {
		this.firstTitle = firstTitle;
	}
	public String getSecondTitle() {
		return secondTitle;
	}
	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<Pic> getPics() {
		return pics;
	}
	public void setPics(List<Pic> pics) {
		this.pics = pics;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Thumb> getThumbs() {
		return thumbs;
	}
	public void setThumbs(List<Thumb> thumbs) {
		this.thumbs = thumbs;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public ZipCode getZip() {
		return zip;
	}

	public void setZip(ZipCode zip) {
		this.zip = zip;
	}
	
	
	
}
