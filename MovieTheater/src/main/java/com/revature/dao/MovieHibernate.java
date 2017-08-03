package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.beans.Movies;

public class MovieHibernate implements MovieDao, HibernateSession {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<Movies> getAllMovies() {
		String hql = "From com.revature.beans.Movies";
		List<Movies> movList = (List<Movies>) session.createQuery(hql).list();
		return movList;
	}
}
