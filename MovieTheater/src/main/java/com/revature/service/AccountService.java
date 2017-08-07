package com.revature.service;

import com.revature.beans.Accounts;

public interface AccountService {
	public Integer saveUser(Accounts account);
	public Accounts login(String username, String password);
}
