package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.beans.Halls;

@Component
public class HallsHibernate implements HallsDao {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<Halls> getHalls() {
		String hql = "From com.revature.beans.Halls";
		List<Halls> hallList = (List<Halls>) session.createQuery(hql).list();
		return hallList;
	}
}
