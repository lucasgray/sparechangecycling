package com.sparechangecycling.daos;

import com.sparechangecycling.hibernate.HibernateSessionFactory;

public abstract class SccDAO {
	
	protected org.hibernate.classic.Session getSession() {
		return HibernateSessionFactory.getSessionFactory().getCurrentSession();
	}
	
}
