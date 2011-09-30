package com.sparechangecycling.web.actions;

import java.util.ArrayList;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.sparechangecycling.daos.SccBikeAdDAO;
import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.Pic;
import com.sparechangecycling.pojos.SccBikeAd;
import com.sparechangecycling.pojos.Thumb;
import com.sparechangecycling.util.ImageHandler;

@UrlBinding("/used-bikes/ads/{$event}/{id}")
public class AdDetailActionBean extends BaseActionBean{

	//to display details or editing
	private Long id;
	
	//for merging edited ad
	private SccBikeAd sccBikeAd;
	private AdType intermediateAdType;
	
	//for deleting an ad & editing
	private Long curUserId;
	
	private SccBikeAdDAO dao;
	
	private FileBean pic;
	
	public AdDetailActionBean() {
		this.dao = new SccBikeAdDAO();
	}

	@DefaultHandler
	@HandlesEvent("singleSccDetail")
	public Resolution viewSccDetail() {
		loadUpAd();
		return new ForwardResolution("/viewSingleAd.jsp");
	}
	
	@HandlesEvent("editAd")
	public Resolution loadAdForEditing() {
		SccBikeAd ad = loadUpAd();
		boolean isBike = AdType.isBike(ad.getType());
		getContext().setTypeForEdit(isBike);
		sccBikeAd = ad;
		return new ForwardResolution("/editSingleAd.jsp");
	}
	
	@HandlesEvent("mergeAd")
	public Resolution mergeAd() {
		
		this.sccBikeAd.setType(intermediateAdType.toString());
		logger.info(intermediateAdType.toString());
		SccBikeAd ad = getContext().getSingleAdBean();
		assert(ad.getId().equals(this.sccBikeAd.getId()));
		
		if (pic != null && pic.getFileName()!="") {
			String picPath = ImageHandler.saveImageToDisk(pic);
			String thumbPath = ImageHandler.getThumbnailFromImage(picPath);
			sccBikeAd.setPics(new ArrayList<Pic>());
			sccBikeAd.setThumbs(new ArrayList<Thumb>());
			sccBikeAd.getPics().add(0, new Pic(picPath));
			sccBikeAd.getThumbs().add(0, new Thumb(thumbPath));
		}
		dao.mergeAd(sccBikeAd);
		
		this.setId(sccBikeAd.getId());
		return viewSccDetail();
	}
	
	@HandlesEvent("deleteAd")
	public Resolution delete() {
		dao.removeAnAd(curUserId, id);
		return new ForwardResolution(SellActionBean.class,"manageAds");
	}
	
	private SccBikeAd loadUpAd() {
		SccBikeAd ad= dao.getSingleAdById(id);
		getContext().setSingleAdBean(ad);
		return ad;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(Long curUserId) {
		this.curUserId = curUserId;
	}

	public SccBikeAd getSccBikeAd() {
		return sccBikeAd;
	}

	public void setSccBikeAd(SccBikeAd sccBikeAd) {
		this.sccBikeAd = sccBikeAd;
	}

	public AdType getIntermediateAdType() {
		return intermediateAdType;
	}

	public void setIntermediateAdType(AdType intermediateAdType) {
		this.intermediateAdType = intermediateAdType;
	}

	public FileBean getPic() {
		return pic;
	}

	public void setPic(FileBean pic) {
		this.pic = pic;
	}
	
}
