package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.beans.Movies;

@Component
public class MovieHibernate implements MovieDao {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<Movies> getAllMovies() {
		return (List<Movies>) session.createCriteria(Movies.class).list();
	}
}
