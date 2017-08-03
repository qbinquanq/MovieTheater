package com.revature.dao;

import com.revature.beans.Accounts;

public interface AccountsDao {
	//This is used for logging in
	Accounts login(String username, String password);

	//This is to create a new user account
	Accounts saveUser(Accounts accounts);
}
