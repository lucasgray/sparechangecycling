package com.sparechangecycling.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sparechangecycling.daos.SccDAO;
import com.sparechangecycling.hibernate.HibernateSessionFactory;
import com.sparechangecycling.pojos.ZipCode;

public class CraigsDAO extends SccDAO {
	
	@SuppressWarnings("unchecked")
	public List<String> getCommunityNames() {
		Session session = HibernateSessionFactory.getSession();
		List list = session.createSQLQuery("SELECT craigsname FROM craigs_cities").list();
		session.close();
		return list;
	}
	
	public ZipCode getDefaultZipCode(String community) {
		Session session = HibernateSessionFactory.getSession();
		String zip = (String)session.createSQLQuery("SELECT zip FROM craigs_cities WHERE craigsname = :name")
			.setString("name", community).uniqueResult();
		ZipCode zc = (ZipCode)session.load(ZipCode.class, zip);
		zc.getZip();
		session.close();
		return zc;
	}
	
	public long getLastEntry(String community) {
		Session session = HibernateSessionFactory.getSession();
		BigInteger bi = (BigInteger)session.createSQLQuery("SELECT MAX(craigs_id) FROM craigsAds WHERE clSubdomain = :commie")
			.setString("commie", community).uniqueResult();
		long retVal = bi == null ? 0 : bi.longValue();
		session.close();
		return retVal;
	}

	@SuppressWarnings("deprecation")
	public Map<String, String> getNearbyCities(String zip, int miles) {
		Map<String, String> cities = new HashMap<String, String>();
		Session session = HibernateSessionFactory.getSession();
		try {
			PreparedStatement ps = session.connection().prepareStatement("call GetNearbyCities(?,?);");
			ps.setString(1, zip);
			ps.setInt(2, miles);
			ResultSet rslts = ps.executeQuery();
			
			while(rslts.next()) {
				cities.put(rslts.getString(1), rslts.getString(2));
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cities;
	}
	
	public ZipCode getZip(String city, String state) {
		Session session = HibernateSessionFactory.getSession();
		try {
			String zip = (String)session.createSQLQuery("SELECT zip FROM zip_codes WHERE city = :city AND state = :state LIMIT 1")
					.setString("city", city).setString("state", state).uniqueResult();
			return (ZipCode)session.load(ZipCode.class, zip);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public int getRate(String community) {
		Session session = HibernateSessionFactory.getSession();
		try {
			return Integer.valueOf((Short)session.createSQLQuery("SELECT rate FROM craigs_cities WHERE craigsname = :name")
				.setString("name", community).uniqueResult());
		} finally {
			session.close();
		}
	}

	public void updateRate(Integer rate, String community) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.createSQLQuery("UPDATE craigs_cities SET rate = :rate WHERE craigsname = :name")
				.setString("name", community).setInteger("rate", rate).executeUpdate();
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
