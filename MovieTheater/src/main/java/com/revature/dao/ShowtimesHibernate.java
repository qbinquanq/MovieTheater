package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.beans.Showtimes;

public class ShowtimesHibernate implements ShowtimesDao, HibernateSession {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<Showtimes> getAllShow() {
		String hql = "From com.revature.beans.Showtimes";
		List<Showtimes> showList = (List<Showtimes>) session.createQuery(hql).list();
		return showList;
	}

}
