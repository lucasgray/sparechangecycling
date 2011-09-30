package com.sparechangecycling.web.actions;

import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.daos.UserDAO;
import com.sparechangecycling.pojos.Message;
import com.sparechangecycling.pojos.User;
import com.sparechangecycling.web.util.Email;
import com.sparechangecycling.web.util.EmailRobot;

@UrlBinding("/used-bikes/user-profile/{$event}")
public class UserActionBean extends BaseActionBean{

	private User user;
	private UserDAO dao;
	
	private Message message;
	private Long recipient;
	private String usrName;
	
	private String passwordChng1;
	private String passwordChng2;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public UserActionBean() {
		dao = new UserDAO();
	}
	
	@DefaultHandler
	@HandlesEvent("editProfile")
	public Resolution editProfile() {
		logger.debug("editProfile called");
		return new ForwardResolution("/editProfile.jsp");
	}
	
	@HandlesEvent("updateProfile")
	public Resolution updateProfile() {
		
		if ((passwordChng1!= null) && (passwordChng1.trim()!="")) {
			if (!passwordChng1.equals(passwordChng2)) {
				getContext().getValidationErrors().add("passwordChng2", new SimpleError("Passwords don't match"));
				return this.getContext().getSourcePageResolution();
			}
		}
		
		dao.updateProfile(getCookie(),user,passwordChng1);
		return new ForwardResolution(SellActionBean.class,"manageAds");
	}
	
	@HandlesEvent("sendMessage") 
	public Resolution sendMessage() {
		
		Long from = getCookie().getId();
		dao.sendMessage(from, recipient, message);
		EmailRobot robot = new EmailRobot();
		Email email = new Email();
		email.createReceivedMessageEmail(getCookie().getUsername(), "lucas.e.gray@gmail.com",message);
//		robot.sendEmail(email);
		
		getContext().getMessages().add(new SimpleMessage("Your message has been successfully sent."));
		
		return new ForwardResolution(SellActionBean.class,"manageAds");
	}
	
	@HandlesEvent("popUpMsgDialog")
	public Resolution popUpMsgDialog(){
		
		//TODO check for login and redirect if necessary
		logger.debug("checking for login");
		
		
		getContext().getRequest().setAttribute("recipient", recipient);
		getContext().getRequest().setAttribute("usrName", usrName);
		return new ForwardResolution("/snippets/message.jsp");
	}
	
	@HandlesEvent("viewMessages")
	public Resolution getMessagesForUser() {
		//keep messages fresh
		List<Message> msgs = dao.getMessages(getCookie());
		
		if (msgs.size()>0){
			Collections.sort(msgs,new Message.MessageSorter());
		}
		getCookie().setReceivedMessages(msgs);

		return new ForwardResolution("/viewMessages.jsp");
	}

	private User getCookie() {
		return getContext().getUserCookie();
	}
	
	@HandlesEvent("viewSingleMessage")
	public Resolution viewSingleMessage(){
		
		//TODO check for login and redirect if necessary
		logger.debug("checking for login");
		
		int i = getCookie().getReceivedMessages().indexOf(message);
		
		dao.markAsRead(getCookie().getReceivedMessages().get(i));
		
		getContext().getRequest().setAttribute("message", getCookie().getReceivedMessages().get(i));
		return new ForwardResolution("/snippets/viewMsg.jsp");
	}

	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Long getRecipient() {
		return recipient;
	}

	public void setRecipient(Long recipient) {
		this.recipient = recipient;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getPasswordChng1() {
		return passwordChng1;
	}

	public void setPasswordChng1(String passwordChng1) {
		this.passwordChng1 = passwordChng1;
	}

	public String getPasswordChng2() {
		return passwordChng2;
	}

	public void setPasswordChng2(String passwordChng2) {
		this.passwordChng2 = passwordChng2;
	}
	
}
