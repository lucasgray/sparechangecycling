package com.sparechangecycling.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sparechangecycling.hibernate.HibernateSessionFactory;

public class CraigsBikesByZipTest {

	public static void main(String[] args) throws HibernateException, SQLException {
		Session sess = HibernateSessionFactory.getSession();
		PreparedStatement st = sess.connection().prepareStatement("call GetNearbyZipCodes(?,?);");
		st.setString(1, "85254");
		st.setInt(2, 100);
		ResultSet rslts = st.executeQuery();
		
		while(rslts.next()) {
			System.out.println(rslts.getString(1));
		}
		
	}
	
}
