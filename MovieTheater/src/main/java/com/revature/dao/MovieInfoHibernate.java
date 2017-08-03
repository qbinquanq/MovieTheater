package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.MovieInfo;
import com.revature.util.HibernateUtil;

public class MovieInfoHibernate implements MovieInfoDao {
	private Logger log = Logger.getRootLogger();
	private HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public List<MovieInfo> getAllMovieInfo() {
		Session s = hu.getSession();
		String hql = "From com.revature.beans.MovieInfo";
		List<MovieInfo> allInfo = (List<MovieInfo>) s.createQuery(hql).list();
		s.close();
		return allInfo;
	}

	@Override
	public MovieInfo saveMovieInfo(MovieInfo movieInfo) {
		Session session = hu.getSession();
		Transaction tx = session.beginTransaction();
		session.save(movieInfo);
		log.warn("MovieInfo saved was: "+movieInfo);
		tx.commit();
		session.close();
		return movieInfo;
	}

}
