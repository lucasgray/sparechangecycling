package com.sparechangecycling.daos;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.pojos.Message;
import com.sparechangecycling.pojos.User;
import com.sparechangecycling.util.PasswordService;

public class UserDAO extends SccDAO {

	private Logger log = LoggerFactory.getLogger(this.getClass());


	/**
	 * 
	 * @param user
	 * @param pass
	 * @return null if invalid, user if valid
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public User userPassValid(String user, String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		Session session = getSession();

		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", user));
		User usr = (User) criteria.uniqueResult();

		if (usr == null) {
			return null;
		}
		

		PasswordService ps = PasswordService.getInstance();
		// hashed w/ pass from user and salt from db
		String hash = ps.encrypt(pass, usr.getSalt());
		// if these match we're good to go
		if (hash.equals(usr.getPass())) {
			
			

			return getUserByUsername(user);
		}

		return null;
	}


	public boolean addUser(User user) {
		Session session = getSession();
		if(userExists(session, user.getUsername(), user.getEmail())) {
			return false;
		}
		try {
			byte[] salt = PasswordService.getInstance().generateEightByteSalt();
			user.setPass(PasswordService.getInstance().encrypt(user.getPass(),salt));
			user.setSalt(salt);
			user.setActive(false);
			session.save(user);
			log.info("added " + user.getEmail() + user.getUsername());
		} catch (Exception e) {
			return false;
		} 
		return true;
	}


	private boolean userExists(Session session, String username, String email) {
		Query query = session.createSQLQuery("SELECT 1 FROM users WHERE username = ? OR email = ?");
		query.setString(0, username).setString(1, email);
		return !query.list().isEmpty();
	}
	
	public boolean sendMessage(Long from, Long to, Message message) {
		Session session = getSession();

		try {
			User fromUsr = (User)session.createCriteria(User.class).add(Restrictions.eq("id", from)).uniqueResult(); 
			User recipientUsr = (User)session.createCriteria(User.class).add(Restrictions.eq("id", to)).uniqueResult(); 
			message.setRecipient(recipientUsr);
			message.setSender(fromUsr);
					
			message.setTimestamp(new Date());
			message.setIsRead(false);
			
			session.merge(fromUsr);
			session.merge(recipientUsr);
			session.save(message);
			log.info("sent message with id "+message.getId()+" from user "+from+" to user "+to);
			
		} catch (Exception e) {
			
			return false;
		} 
		return true;
	}
	
	public boolean deleteMessage(Message message) {
		Session session = getSession();
		
		try {
			session.delete(message);
			log.info("deleted message "+message.getId());
			
		} catch (Exception e) {
			
			return false;
		} 
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(User user) {
		Session session = getSession();

		List<Message> msgs = null;
		try {
			msgs=(List<Message>)session.createCriteria(Message.class).add(Restrictions.eq("recipient", user)).list();
			
			//log.info("pulled messages for user "+userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return msgs;
	}
	


	public void markAsRead(Message msg) {
		Session session = getSession();
		
		try {
			msg.setIsRead(true);
			session.merge(msg);
			log.info("marked msg "+msg.getId()+" as read");
			
		} catch (Exception e) {
			
		} 
	}
	
	public boolean hasUnreadMessages(User user) {
		Session session = getSession();
		int size=0;
		try {
			Criteria crit = session.createCriteria(Message.class);
			crit.add(Restrictions.eq("recipient", user));
			crit.add(Restrictions.eq("isRead", Boolean.FALSE));
			size = crit.list().size();
			log.info("unread msgs: "+size);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		return size > 0;
	}

	public void updateProfile(User userCookie, User user, String passwordChng1) {
		if (user != null) {
			if (user.getZip()!= null) {
				userCookie.setZip(user.getZip());
			}
			if ((user.getEmail()!= null) && (!user.getEmail().trim().equals(""))) {
				userCookie.setEmail(user.getEmail());
			}
		}
		if ((passwordChng1!= null) && (!passwordChng1.trim().equals(""))) {
			PasswordService ps = PasswordService.getInstance();
			try {
				byte[] salt = ps.generateEightByteSalt();
				String encryptedPass = ps.encrypt(passwordChng1, salt);
				userCookie.setSalt(salt);
				userCookie.setPass(encryptedPass);
			} catch(Exception e) {
				log.error("couldn't create salt and encrypt pass ");
			}
		}
		
		Session session = getSession();
		
		try {
			session.merge(userCookie);
			
			log.info("saved updated info for user "+userCookie.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		
		} 
	}
	
	public int getAmtAdsByUser(Long userId) {
		Session session = getSession();

		User user;
		int ads = 0;
		try {
			user=(User)session.createCriteria(User.class).add(Restrictions.eq("id", userId)).uniqueResult();
			//lazy loaded, pull em
			if (user.getAds().size()>0) ads = user.getAds().size();
			log.info("pulled amt of ads for user "+user.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ads;
	}

	public User getUserByUsername(String custom) {
		Session session = getSession();

		User user = null;
		try {
			user=(User)session.createCriteria(User.class).add(Restrictions.eq("username", custom)).uniqueResult();
			
			//lazy load before we throw it into session...
			if (user.getSubscription() != null) {
				user.getSubscription().isActive();
			}
			if (user.getZip() != null) {
				user.getZip().getZip();
			}
			if (user.getProfile() != null) {
				user.getProfile().getAddrOne();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;

	}

	public void saveUser(User user) {
		Session session = getSession();
		try {
			session.merge(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public User getUserByEmail(String email) {
		Session session = getSession();
		if(!userExists(session, "", email)) {
			return null;
		}
		User user = null;
		try {
			user=(User)session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}

}
