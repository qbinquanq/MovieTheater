package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.revature.beans.WalkIn;

@Component
public class WalkInHibernate implements WalkInDao {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Integer saveWalkIn(WalkIn walk) {
		return (Integer) session.save(walk);
	}
}
