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
		return (Accounts) session.createCriteria(Accounts.class).add(Restrictions.eq("uname", uname))
				.add(Restrictions.eq("pword", pword)).uniqueResult();
	}

	@Override
	public Integer saveUser(Accounts account) {
		return (Integer) session.save(account);
	}
}
