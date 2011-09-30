package com.sparechangecycling.web.actions;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.sparechangecycling.daos.UserDAO;
import com.sparechangecycling.pojos.Profile;
import com.sparechangecycling.pojos.User;

@UrlBinding("/used-bikes/profile/{$event}/{user}")
public class ProfileActionBean extends BaseActionBean {

	private UserDAO userDao;
	
	private String user;
	
	private Profile profile;
	
	public ProfileActionBean() {
		userDao = new UserDAO();
	}
	
	@HandlesEvent("createOrModify")
	public Resolution createOrModify() {
		return new ForwardResolution("/modifyProfile.jsp");
	}
	
	@HandlesEvent("modify")
	public Resolution doTheModddd() {
		
		User user = userDao.getUserByUsername(getContext().getUserCookie().getUsername());
		Profile oldProfile = user.getProfile();
		oldProfile = null;
		user.setProfile(profile);
		userDao.saveUser(user);
		
		logger.info("saved profile");
		
		//TODO add a message like hooray you did it, see messages (to and from users)
		return new ForwardResolution("/manageAds.jsp");
	}
	
	@HandlesEvent("view")
	public Resolution viewProfile() {
		logger.info(user);
		User usr = userDao.getUserByUsername(user);
		
		//tell hibernate to pull profile now
		usr.getProfile().getAddrOne();
		
		getContext().getRequest().setAttribute("user", usr);
		return new ForwardResolution("/viewProfile.jsp");
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
