package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.revature.beans.MovieInfo;

@Component
public class MovieInfoHibernate implements MovieInfoDao {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<MovieInfo> getAllMovieInfo() {
		return (List<MovieInfo>) session.createCriteria(MovieInfo.class).list();
	}

	@Override
	public Integer saveMovieInfo(MovieInfo movieInfo) {
		return (Integer) session.save(movieInfo);
	}
}
