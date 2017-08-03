package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.WalkIn;

public class WalkInHibernate implements WalkInDao, HibernateSession {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public WalkIn saveWalkIn(WalkIn walk) {
		Transaction tx = session.beginTransaction();
		session.save(walk);
		tx.commit();
		return walk;
	}

}
