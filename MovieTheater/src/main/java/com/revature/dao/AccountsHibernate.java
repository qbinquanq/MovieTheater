package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Accounts;
import com.revature.util.HibernateUtil;

public class AccountsHibernate implements AccountsDao {
	private static Logger log = Logger.getRootLogger();
	private static HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public Accounts login(String uname, String pword) {
		Session session = hu.getSession();
		Transaction tx = session.beginTransaction();
		String hql = ("from Accounts where uname =:uname and pword =:pword");
		Query query = session.createQuery(hql);
		query.setString("uname", uname);
		query.setString("pword", pword);
		List userinfo = query.list();
		if (userinfo != null && userinfo.size() > 0) {
			return (Accounts) userinfo.get(0);
		} else
			return null;
	}

	@Override
	public Accounts save(Accounts accounts) {
		Session session = hu.getSession();
		Transaction tx = session.beginTransaction();
		session.save(accounts);
		log.warn("The user is: " + accounts);
		tx.commit();
		session.close();
		return accounts;
	}
	
	@Override
	public Accounts getById(int id){
		Session s = hu.getSession();
		String hql = "from Accounts where userId=:userId";
		Query query = s.createQuery(hql);
		query.setInteger("userId", id);
		List userList = query.list();
		if (userList != null && userList.size() > 0) {
			return (Accounts) userList.get(0);
		} else
			return null;
	}
}
