package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Accounts;
import com.revature.dao.AccountsDao;

@Repository
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountsDao ad;

	public AccountsDao getAd() {
		return ad;
	}

	public void setAd(AccountsDao ad) {
		this.ad = ad;
	}

	@Override
	public Integer saveUser(Accounts account) {
		return ad.saveUser(account);
	}

	@Override
	public Accounts login(String username, String password) {
		return ad.login(username, password);
	}
}
