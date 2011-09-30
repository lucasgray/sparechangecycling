package com.sparechangecycling.web.actions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.UrlBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.sparechangecycling.daos.UserDAO;
import com.sparechangecycling.pojos.Subscription;
import com.sparechangecycling.pojos.User;

@UrlBinding("/used-bikes/payment/")
public class SubscribedActionBean extends BaseActionBean {
//https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_notify-validate&mc_gross=19.95&protection_eligibility=Eligible&address_status=confirmed&payer_id=LPLWNMTBWMFAY&tax=0.00&...&payment_gross=19.95&shipping=0.00
	
	private String mc_gross;
	private String address_status;
	private String payer_id;
	private String payment_status;
	private String custom;
	private String item_name;
	private String txn_id;

	private UserDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public SubscribedActionBean() {
		dao = new UserDAO();
	}
	
	//TODO lots of hardcoded shizzy in here and upgrade.jsp,
	//fix with properties file.
	@DefaultHandler
	public void confirmSubscriptionPaymentAndUpdate() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	
		logger.info("yay, we sold a subscription!");
		
		logger.info(mc_gross+address_status+payer_id+payment_status+custom+item_name);
		
		final WebClient webClient = new WebClient();
		
		Enumeration en = getContext().getRequest().getParameterNames();
		String querystring = "cmd=_notify-validate";
		while(en.hasMoreElements()){
			String paramName = (String)en.nextElement();
			String paramValue = getContext().getRequest().getParameter(paramName);
			querystring = querystring + "&" + paramName + "=" + URLEncoder.encode(paramValue);
		}
		
		WebRequestSettings req = new WebRequestSettings(new URL("https://www.sandbox.paypal.com/cgi-bin/webscr?"+querystring));
	    WebResponse resp = webClient.loadWebResponse(req);
	    
	    logger.info(resp.getContentAsString());
	    
	    //TODO need to handle a cancellation too...
	    
	    if (resp.getContentAsString().equals("VERIFIED")
	    		&& payment_status.equalsIgnoreCase("Completed")) {
	    	User user = dao.getUserByUsername(custom);
	    	
	    	Subscription sub = new Subscription();
	    	sub.setConfirmationCode(txn_id);
	    	sub.setMemberSince(new Date());
	    	sub.setType(item_name);
	    	sub.setActive(true);
	    	
	    	user.setSubscription(sub);
	    	
	    	dao.saveUser(user);
	    	getContext().setUserCookie(user);
	    }
	}

	public String getMc_gross() {
		return mc_gross;
	}

	public void setMc_gross(String mc_gross) {
		this.mc_gross = mc_gross;
	}

	public String getAddress_status() {
		return address_status;
	}

	public void setAddress_status(String address_status) {
		this.address_status = address_status;
	}

	public String getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}	
	
}
