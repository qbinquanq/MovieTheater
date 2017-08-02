package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.revature.beans.Movies;
import com.revature.util.HibernateUtil;

public class MovieHibernate implements MovieDao {
	private HibernateUtil hu = HibernateUtil.getInstance();
	private Logger log = Logger.getRootLogger();

	@Override
	public List<Movies> getAllMovies() {
		Session s = hu.getSession();
		String hql = "From com.revature.beans.Movies";
		List<Movies> movList = (List<Movies>) s.createQuery(hql).list();
		s.close();
		return movList;
	}
}