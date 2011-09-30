package com.sparechangecycling.web.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.SessionScope;

import com.sparechangecycling.daos.UserDAO;
import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.Article;
import com.sparechangecycling.pojos.SccBikeAd;
import com.sparechangecycling.pojos.User;
import com.sparechangecycling.util.SearchPreferences;

@SessionScope
public class SccActionBeanContext extends ActionBeanContext{


    public void setUserCookie(User user) {
    	this.getRequest().getSession().setAttribute("user", user);
    }
    public User getUserCookie() {
    	return (User)this.getRequest().getSession().getAttribute("user");
    }

    public void setSearchPreferences(SearchPreferences pref) {
    	//logger.info("setting search preferences: "+pref.getType());
    	this.getRequest().getSession(true).setAttribute("pref", pref);
    }
    public SearchPreferences getSearchPreferences(){
    	
    	SearchPreferences pref = (SearchPreferences)this.getRequest().getSession().getAttribute("pref");
    	if (pref == null) {
    		List<AdType> type = new ArrayList<AdType>();
    		type.add(AdType.ALL);
    		
    		SearchPreferences newPref = new SearchPreferences(new Date(),type,"",0,0,0,""); 
    		setSearchPreferences(newPref);
    		return newPref;
    	}
    	
    	return pref;
    }
    public void setSingleAdBean(SccBikeAd info) {
    	this.getRequest().setAttribute("singleAd",info);
    }
    public SccBikeAd getSingleAdBean(){
    	return (SccBikeAd)this.getRequest().getAttribute("singleAd");
    }

	public List<AdType> getBikeTypes() {	
		return AdType.getBikeTypes();
	}
	
	public List<AdType> getPartTypes() {
		return AdType.getPartTypes();
	}
	
	public void setTypeForEdit(boolean isBike) {
		this.getRequest().setAttribute("isBike",isBike);
	}
	
	public void setSingleArticleBean(Article article) {
		this.getRequest().setAttribute("article",article);
	}
	public Article getSingleArticleBean() {
		return (Article)this.getRequest().getAttribute("article");
	}
	
	public void setHasUnreadMessages() {
		UserDAO dao = new UserDAO();
		this.getRequest().getSession().setAttribute("hasMsgs",dao.hasUnreadMessages(getUserCookie()));
	}
}
