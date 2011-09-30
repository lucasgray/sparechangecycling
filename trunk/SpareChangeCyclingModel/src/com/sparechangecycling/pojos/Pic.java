package com.sparechangecycling.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sparechangecycling.util.ImageHandler;

@Entity
@Table(name="scc_pics")
public class Pic {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length=255)
	private String href;
	
	public Pic() {super();}
	
	public Pic(String href) {
		this.href = href;
	}

	public String getHref() {
		return ImageHandler.getHttpPrefix()+href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
