package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static HibernateUtil hu;
	private SessionFactory sessionFactory;

	private HibernateUtil() {
		super();
	}

	public synchronized static HibernateUtil getInstance() {
		if (hu == null) {
			hu = new HibernateUtil();
		}
		return hu;
	}

	public synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration conf = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
					.build();
			sessionFactory = conf.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}

	public Session getSession() {
		return this.getSessionFactory().openSession();
	}

}