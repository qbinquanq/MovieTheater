package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.WalkIn;
import com.revature.util.HibernateUtil;

public class WalkInHibernate implements WalkInDao {
	private HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public WalkIn saveWalkIn(WalkIn walk) {
		Session s = hu.getSession();
		Transaction tx = s.beginTransaction();
		s.save(walk);
		tx.commit();
		s.close();
		return walk;
	}

}
