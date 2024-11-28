package com.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Failed to create SessionFactory: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Session getSession() {
		return sessionFactory.openSession();
	}
	
	public static void close() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
