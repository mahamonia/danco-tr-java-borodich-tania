package com.borodich.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

public class UtilityForHibernate {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(UtilityForHibernate.class);
	private static SessionFactory sessionFactory = null;

	static {
		try {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable e) {
			log.info(e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}

}
