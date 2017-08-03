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
		String hql = "From com.revature.beans.MovieInfo";
		List<MovieInfo> allInfo = (List<MovieInfo>) session.createQuery(hql).list();
		return allInfo;
	}

	@Override
	public MovieInfo saveMovieInfo(MovieInfo movieInfo) {
		session.save(movieInfo);
		return movieInfo;
	}
}
