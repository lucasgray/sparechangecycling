package com.sparechangecycling.web.actions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.daos.SccBikeAdDAO;
import com.sparechangecycling.daos.UserDAO;
import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.Pic;
import com.sparechangecycling.pojos.SccBikeAd;
import com.sparechangecycling.pojos.Thumb;
import com.sparechangecycling.util.ImageHandler;

@UrlBinding("/used-bikes/{$event}")
public class SellActionBean extends BaseActionBean{
	
	private SccBikeAd sccBikeAd;
	
	private SccBikeAdDAO sccBikeAdDao;
	private UserDAO userDao;
	
	private FileBean pic;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int NUM_OF_FREE_ADS = 5;
	
	//doing things this way keeps the db just a little more self-explanatory
	private AdType intermediateAdType;	
	
	public SellActionBean() {
		sccBikeAdDao = new SccBikeAdDAO();
		sccBikeAd = new SccBikeAd();
		userDao = new UserDAO();
	}
	
	@DefaultHandler
	@HandlesEvent("begin")
	public Resolution begin() throws IOException {
		
		if (getContext().getUserCookie() == null) {
			getContext().getValidationErrors().add("field",
					new SimpleError("need to login first"));
			return new ForwardResolution("/login.jsp");
		}

		else if (!isAbleToAddMoreAds()) {
			logger.info("user wasn't able to add another ad!");
			return new ForwardResolution("/upgrade.jsp");
		}
		
		return new ForwardResolution("/sell.jsp");
		
	}
	
	@HandlesEvent("upgrade")
	public Resolution upgrade() {
		return new ForwardResolution("/upgrade.jsp");
	}

	private boolean isAbleToAddMoreAds() {
		boolean isOverLimit = userDao.getAmtAdsByUser(getContext().getUserCookie().getId())>NUM_OF_FREE_ADS;
		boolean isSubscribed = getContext().getUserCookie().getSubscription() != null && 
					getContext().getUserCookie().getSubscription().isActive();
		
		return isSubscribed || !isOverLimit;
	}
	
	@HandlesEvent("createBike")
	public Resolution create() throws MalformedURLException {
		return addAnAd();
	}
	@HandlesEvent("createPartAd")
	public Resolution createPart() throws MalformedURLException {
		logger.info("creating part");
		sccBikeAd.setSecondTitle(intermediateAdType.toString());
		return addAnAd();
	}
	
	private Resolution addAnAd() throws MalformedURLException {
		
		if (getContext().getUserCookie() == null) {
			getContext().getValidationErrors().add("field",new SimpleError("need to login first"));
			return new ForwardResolution("/login.jsp");
		}
		
		checkForBot();
		
		logger.info("picture info: name "+pic.getFileName()+" size " +pic.getSize());

		String picPath = ImageHandler.saveImageToDisk(pic);
		String thumbPath = ImageHandler.getThumbnailFromImage(picPath);
		
		Long uid = getContext().getUserCookie().getId();
		
		sccBikeAd.setType(intermediateAdType.toString());
		sccBikeAd.setZip(getContext().getUserCookie().getZip());
		sccBikeAd.setDate(new Date());	 
		
		sccBikeAd.getThumbs().add(new Thumb(thumbPath));
		sccBikeAd.getPics().add(new Pic(picPath));
		
		logger.info("thumb path: "+thumbPath);
		logger.info("pic path: "+picPath);
		sccBikeAdDao.addAnAd(uid, sccBikeAd);
		
		return new ForwardResolution("index.jsp");

	}
	
	@HandlesEvent("manageAds") 
	public Resolution manage() {
		if (getContext().getUserCookie() == null) {
			getContext().getValidationErrors().add("field",new SimpleError("need to login first"));
			return new ForwardResolution("/login.jsp");
		}
		
		//refresh our user cookie now!
		getContext().setUserCookie(userDao.getUserByUsername(getContext().getUserCookie().getUsername()));
		
		List<SccBikeAd> ads = getContext().getUserCookie().getAds();
		getContext().getRequest().setAttribute("ads", ads);
		
		return new ForwardResolution("/manageAds.jsp");
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
