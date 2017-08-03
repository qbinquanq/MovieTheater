package com.revature.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Accounts;

public class AccountsHibernate implements AccountsDao, HibernateSession {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Accounts login(String uname, String pword) {
		Criteria crit = session.createCriteria(Accounts.class).add(Restrictions.eq("uname", uname))
				.add(Restrictions.eq("pword", pword));
		Accounts userinfo = (Accounts) crit.uniqueResult();
		return userinfo;
	}

	@Override
	public Accounts saveUser(Accounts account) {
		Transaction tx = session.beginTransaction();
		session.save(account);
		tx.commit();
		return account;
	}
}
