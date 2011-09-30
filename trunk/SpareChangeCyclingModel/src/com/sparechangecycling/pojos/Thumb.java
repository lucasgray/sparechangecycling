package com.sparechangecycling.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sparechangecycling.util.ImageHandler;

@Entity
@Table(name="scc_thumb")
public class Thumb {

	@Id
	@GeneratedValue
	private Long id;
	private String href;
	
	public Thumb() {super();}
	public Thumb(String String) {
		this.href = String;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHref() {
		return href;//ImageHandler.getHttpPrefix()+ImageHandler.getThumbHttpPrefix()+href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	
}
