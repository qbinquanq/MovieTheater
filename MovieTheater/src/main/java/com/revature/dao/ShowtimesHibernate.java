package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.beans.Showtimes;
import com.revature.util.HibernateUtil;

public class ShowtimesHibernate implements ShowtimesDao {
	private HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public List<Showtimes> getAllShow() {
		Session s = hu.getSession();
		String hql = "From com.revature.beans.Showtimes";
		List<Showtimes> showList = (List<Showtimes>) s.createQuery(hql).list();
		s.close();
		return showList;
	}

}
