package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.beans.Showtimes;

@Component
public class ShowtimesHibernate implements ShowtimesDao {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<Showtimes> getAllShow() {
		return (List<Showtimes>) session.createCriteria(Showtimes.class).list();
	}
}
