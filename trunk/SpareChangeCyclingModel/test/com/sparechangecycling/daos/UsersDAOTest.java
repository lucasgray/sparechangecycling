package com.sparechangecycling.daos;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sparechangecycling.hibernate.HibernateSessionFactory;
import com.sparechangecycling.pojos.Subscription;
import com.sparechangecycling.pojos.User;

public class UsersDAOTest {

	public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException {
		UserDAO dao = new UserDAO();
//		User user = new User();
//		user.setUsername("testuser");
//		user.setEmail("test email");
//		user.setPass("testpass");
//		dao.addUser(user);
		
//		Message message = new Message();
//		message.setBody("hello");
//		message.setTitle("goodbye");
//		
//		User user1 = dao.userPassValid("lucas", "gray");
//		User user2 = dao.userPassValid("my", "humps");
//		
//		dao.sendMessage(user1.getId(), user2.getId(), message);
//		dao.hasUnreadMessages(user1);

		User subscribed = dao.userPassValid("subscribed", "subscribed");
		Subscription sub = new Subscription();
		sub.setActive(true);
		sub.setConfirmationCode("abc123doreme");
		sub.setMemberSince(new Date());
		sub.setType("1 month recurring");
		
		subscribed.setSubscription(sub);
		
		dao.saveUser(subscribed);
	}
}
