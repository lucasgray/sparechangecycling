package com.sparechangecycling.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "craigs_cities")
public class Community {

	@Id
	@Column(name = "craigsname")
	private String name;
	@OneToOne(targetEntity=ZipCode.class,fetch=FetchType.LAZY)
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="zip")
	private ZipCode zipCode;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ZipCode getZipCode() {
		return zipCode;
	}
	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
