package com.revature.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Accounts;
import com.revature.util.HibernateUtil;

public class AccountsHibernate implements AccountsDao {
	private static Logger log = Logger.getRootLogger();
	private static HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public Accounts login(String uname, String pword) {
		Session s = hu.getSession();
		Criteria crit = s.createCriteria(Accounts.class).add(Restrictions.eq("uname", uname))
				.add(Restrictions.eq("pword", pword));
		Accounts userinfo = (Accounts) crit.uniqueResult();
		s.close();
		return userinfo;
	}

	@Override
	public Accounts save(Accounts account) {
		Session session = hu.getSession();
		Transaction tx = session.beginTransaction();
		session.save(account);
		log.warn("Account saved was: "+account);
		tx.commit();
		session.close();
		return account;
	}
}
