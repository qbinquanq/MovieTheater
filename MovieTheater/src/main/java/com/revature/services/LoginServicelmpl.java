package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.Accounts;
import com.revature.dao.AccountsDao;

@Component
public class LoginServicelmpl implements LoginService{
	@Autowired
	private AccountsDao ad;

	@Override
	public Accounts login(String username, String password) {
		return ad.login(username, password);
	}
}
