package com.sparechangecycling.web.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import com.sparechangecycling.pojos.Message;
import com.sparechangecycling.pojos.User;
import com.sparechangecycling.util.PasswordService;

public class Email {

	private String body;
	private String title;
	private String recipient;
	private String sender;
	
	public static void createForgotPasswordEmail(String email, String newPass) {
		String subject = "SpareChangeCycling Forgotten Password";
		StringBuilder message = new StringBuilder();
		message.append("Hello from SpareChangeCyling!\n\n");
		message.append("Your password has been reset.\n\n");
		message.append("Your new password is: ");
		message.append(newPass); 
		message.append(" .\n\n");
		EmailRobot.sendEmail(email, "mail-robot@sparechangecycling.com", subject, message);
	}
	
	public Email createReceivedMessageEmail(String senderUsername, String recipientEmail, Message message) {
		
		Email email = new Email();
		email.title = "You've received a new Spare Change message from "+senderUsername;
		email.sender = "mail-robot@sparechangecycling.com";
		email.recipient = recipientEmail;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Hello from SpareChangeCycling<br/><br/>");
		sb.append("You've received a new message from ").append(senderUsername).append(".<br/><br/>");
		sb.append("\"").append(message.getTitle()).append(message.getBody()).append("\"<br/><br/>");
		sb.append("<a href=\"http://sparechangecycling.com/used-bikes/login/begin\">Login to see it</a><br/>");
		sb.append("Sincerely,<br/>");
		sb.append("The Spare Change Cycling Mail Robot");
		
		return email;
	}
	
	public static void sendAccountConfirmEmail(User user) {
		try {
			URLCodec codec = new URLCodec();
			String regId = PasswordService.INSTANCE.encrypt(user.getPass(), new byte[]{});
			String subject = "SpareChangeCycling Registration";
			StringBuilder message = new StringBuilder();
			message.append("Hello from SpareChangeCyling!\n\n");
			message.append("Thanks for joining!\n\n");
			message.append("To complete your registration: \n");
			message.append("http://www.sparechangecycling.com/used-bikes/login/confirm?email=");
			message.append(codec.encode(user.getEmail())); 
			message.append("&confirmid=");
			message.append(codec.encode(regId)); 
			message.append("\n\n");
			message.append("If you didn't register at SpareChangeCyling, please disregard this message.");
			EmailRobot.sendEmail(user.getEmail(), "the.buffalo@sparechangecycling.com", subject, message);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error sending account email.");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Error sending account email.");
		} catch (EncoderException e) {
			throw new RuntimeException("Error sending account email.");
		}
		
	}
	
	public Email createSubscribedEmail(String user, String typeOfSub) {
		
		return null;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
}
