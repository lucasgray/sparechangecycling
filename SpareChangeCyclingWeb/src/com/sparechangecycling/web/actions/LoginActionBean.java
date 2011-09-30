package com.sparechangecycling.web.actions;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.daos.UserDAO;
import com.sparechangecycling.pojos.User;
import com.sparechangecycling.pojos.ZipCode;
import com.sparechangecycling.util.PasswordService;
import com.sparechangecycling.web.util.Email;

@UrlBinding("/used-bikes/login/{$event}")
public class LoginActionBean extends BaseActionBean implements ActionBean{

	private static final Logger log = LoggerFactory.getLogger(LoginActionBean.class);
	
	private String username;
	private String email;
	private String pass;
	private String zip;
	
	private String sourcePageForLogin;

	private UserDAO dao;
	
	public LoginActionBean() {
		dao = new UserDAO();
	}
	
	@HandlesEvent("login")
	public Resolution loggit() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		checkForBot();
		
		User user = dao.userPassValid(username, pass);
		if (user != null) {
			return validUser(user);
		} else { 
			getContext().getValidationErrors().add("field",new SimpleError("bad username or password"));
			return getContext().getSourcePageResolution();
		}
	}

	private Resolution validUser(User user) {
		log.info("valid user/pass: " + username);
		getContext().setUserCookie(user);
		
		if (getSourcePage() != null) {
			return new RedirectResolution(getSourcePage());
		}
		
		return new ForwardResolution(SellActionBean.class,"manageAds");
	}
	
	@HandlesEvent("confirm")
	public Resolution confirm() throws Exception {
		//if user is already active, skip this
		HttpServletRequest request = getContext().getRequest();
		String email = request.getParameter("email");
		User user = dao.getUserByEmail(email);
		if(user == null || user.isActive()) {
			return new ForwardResolution("/registrationError.jsp"); 
		}	
		String confirmid = request.getParameter("confirmid");
		String regId = PasswordService.INSTANCE.encrypt(user.getPass(), new byte[]{});
		
		if(regId.equals(confirmid)) {
			user.setActive(true);
			dao.saveUser(user);
			return validUser(user); 
		} 
		return new ForwardResolution("/registrationError.jsp");
		
	}
	
	@HandlesEvent("register")
	public Resolution reg() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		checkForBot();
		log.info("registering: " + username);
		User user = new User(username,email,pass);
		user.setZip(new ZipCode(zip));
		//TODO: what if zip not found in db?
		boolean registerSuccess = dao.addUser(user);
		
		if (registerSuccess) {
			Email.sendAccountConfirmEmail(user);
			return new ForwardResolution("/registrationReceived.jsp");
		} else {
			this.getContext().getValidationErrors().add("nuddin", new SimpleError("duplicate email or username"));
			return new ForwardResolution("/register.jsp");
		}
	}
	
	@HandlesEvent("begin-registration")
	public Resolution regBegin() {
		return new ForwardResolution("/register.jsp");
	}
	
	@HandlesEvent("forgot-password")
	public Resolution forgotPassword() {
		return new ForwardResolution("/forgotPassword.jsp");
	}
	
	
	@DefaultHandler
	@HandlesEvent("begin")
	public Resolution begin() {
		
		System.out.println("source page ! "+this.sourcePageForLogin);
		setSourcePage(this.sourcePageForLogin);
		
		return new ForwardResolution("/login.jsp");
	}
	
	@HandlesEvent("logout")
	public Resolution logout() {
		getContext().getRequest().getSession().invalidate();
		
		return new RedirectResolution("/index.jsp");
	}
	
	@HandlesEvent("reset-password")
	public Resolution resetPassword() {	
		String newPassword = RandomStringUtils.randomAlphanumeric(10);
		User user = dao.getUserByEmail(email);
		//if user doesn't exist, still redirect to the same page, 
		//dont want to tell existance of user with that email
		if(user!=null) {
			dao.updateProfile(user, null, newPassword);
			Email.createForgotPasswordEmail(email, newPassword);
		}
		return new ForwardResolution("/passwordReset.jsp");
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getSourcePageForLogin() {
		return sourcePageForLogin;
	}

	public void setSourcePageForLogin(String sourcePageForLogin) {
		this.sourcePageForLogin = sourcePageForLogin;
	}

	
}
