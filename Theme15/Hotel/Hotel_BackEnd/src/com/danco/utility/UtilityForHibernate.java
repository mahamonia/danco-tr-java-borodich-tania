package com.danco.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

import com.danco.api.backend.IUtilityForHibernate;

public class UtilityForHibernate implements IUtilityForHibernate {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UtilityForHibernate.class);
	public UtilityForHibernate(){
		
	}

	@Override
	public SessionFactory getSessionFactory() {
		SessionFactory sessionFactory=null;
		try {
			 Configuration configuration = new Configuration().configure();
	            ServiceRegistry serviceRegistry
	                = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();
	             System.out.println("Configuration load");
	            // builds a session factory from the service registry
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
	            System.out.println("Session factory create");
		} catch (Throwable e) {
			 System.out.println("Initial SessionFactory creation failed. :" + e);
			 log.info(e.getMessage());			 
	            throw new ExceptionInInitializerError(e);
		}		
		return sessionFactory;
	}
 

}
