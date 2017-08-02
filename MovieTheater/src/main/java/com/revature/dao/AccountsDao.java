package com.revature.dao;

import java.util.List;

import com.revature.beans.Accounts;

public interface AccountsDao {
	List<Accounts> getAll();
	Accounts login(String username, String password);
	Accounts save (Accounts accounts);
	void updateAccounts();
}
