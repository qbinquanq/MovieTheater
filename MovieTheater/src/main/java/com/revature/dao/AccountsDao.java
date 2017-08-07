package com.revature.dao;

import com.revature.beans.Accounts;

public interface AccountsDao extends HibernateSession{
	//This is used for logging in
	Accounts login(String username, String password);

	//This is to create a new user account
	Integer saveUser(Accounts accounts);
}
