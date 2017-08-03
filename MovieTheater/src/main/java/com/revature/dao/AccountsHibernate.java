package com.revature.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.revature.beans.Accounts;

@Component
public class AccountsHibernate implements AccountsDao {
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
	public void saveUser(Accounts account) {
		session.save(account);
	}
}
