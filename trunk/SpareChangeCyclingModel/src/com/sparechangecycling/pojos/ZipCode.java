package com.sparechangecycling.pojos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zip_codes")
public class ZipCode {
	
	@Id
	private String zip;
	private double latitude;
	private double longitude;
	
	public ZipCode(double latitude, double longitude, String zip) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.zip = zip;
	}
	
	public ZipCode() {
		
	}

	public ZipCode(String zip) {
		this.zip = zip;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return zip;
	}
	
	

}
