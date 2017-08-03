package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.beans.Halls;
import com.revature.util.HibernateUtil;

public class HallsHibernate implements HallsDao {
	private HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public List<Halls> getHalls() {
		Session s = hu.getSession();
		String hql = "From com.revature.beans.Halls";
		List<Halls> hallList = (List<Halls>) s.createQuery(hql).list();
		s.close();
		return hallList;
	}

}
