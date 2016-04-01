package com.danco.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.danco.api.backend.IUtilityForHibernate;

public class UtilityForHibernate implements IUtilityForHibernate {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UtilityForHibernate.class);
	public UtilityForHibernate(){
		
	}

	@Override
	public SessionFactory getSessionFactory() {
		SessionFactory sessionFactory=null;
		try {
			Configuration configuration = new Configuration();
	         configuration.configure("hibernate.cfg.xml");
	         System.out.println("Hibernate Configuration loaded");
	         
	         sessionFactory=configuration.buildSessionFactory();
		} catch (Throwable e) {
			 System.out.println("Initial SessionFactory creation failed. :" + e);
			 log.info(e.getMessage());			 
	            throw new ExceptionInInitializerError(e);
		}
		 
		
		return sessionFactory;
	}

}
