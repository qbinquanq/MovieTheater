package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.MovieInfo;

public class MovieInfoHibernate implements MovieInfoDao, HibernateSession {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<MovieInfo> getAllMovieInfo() {
		String hql = "From com.revature.beans.MovieInfo";
		List<MovieInfo> allInfo = (List<MovieInfo>) session.createQuery(hql).list();
		return allInfo;
	}

	@Override
	public MovieInfo saveMovieInfo(MovieInfo movieInfo) {
		Transaction tx = session.beginTransaction();
		session.save(movieInfo);
		tx.commit();
		return movieInfo;
	}
}
