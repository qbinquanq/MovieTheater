package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.Accounts;
import com.revature.dao.AccountsDao;

@Component
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountsDao ad;
	
	public AccountsDao getAd(){
		return ad;
	}
	
	public void setAd(AccountsDao ad){
		this.ad = ad;
	}
	
	@Override
	public Integer saveUser(Accounts account) {
		Integer i = ad.saveUser(account);
		return i;
	}
	

}
