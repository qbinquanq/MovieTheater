package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Accounts;
import com.revature.beans.Transactions;
import com.revature.dao.TransactionsDao;

@Repository
public class TransactionsServiceImpl implements TransactionsService{

	@Autowired
	private TransactionsDao td;
	
	public TransactionsDao getTd(){
		return td;
	}
	
	public void setTd(TransactionsDao td){
		this.td = td;
	}

	@Override
	public Integer saveTransaction(Transactions transaction, int amt) {
		return td.saveTransaction(transaction, amt);
	}

	@Override
	public List<Transactions> getAllTransactions() {
		return td.getAllTransactions();
	}

	@Override
	public void deleteTransaction(Transactions t) {
		td.deleteTransaction(t);	
	}

	@Override
	public List<Transactions> getByUser(Accounts user) {
		return td.getByUser(user);
	}
}
