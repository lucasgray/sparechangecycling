package com.sparechangecycling.web.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.sparechangecycling.property.PropertyManager;

public class EmailRobot {

	public static void sendEmail(CharSequence to, CharSequence from, CharSequence subject, CharSequence message) {
		SimpleEmail email = new SimpleEmail();
		PropertyManager in = new PropertyManager("mail.properties");
		try {
			email.setHostName(in.get("scc.mail.smtp.host"));
			email.addTo(to.toString());
			email.setFrom(from.toString());
			email.setSubject(subject.toString());
			email.setMsg(message.toString());
//			email.setBounceAddress("");
			email.setAuthentication(in.get("scc.mail.username"), in.get("scc.mail.password"));
			email.setSmtpPort(in.getInt("scc.mail.smtp.port"));
			email.setTLS(true);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
}
