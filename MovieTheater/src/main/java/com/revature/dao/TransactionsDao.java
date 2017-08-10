package com.revature.dao;

import java.util.List;

import com.revature.beans.Accounts;
import com.revature.beans.Transactions;

public interface TransactionsDao extends HibernateSession {
	//This is to save Transations
	public Integer saveTransaction(Transactions transaction, int amt);
	
	//This is to get a list of Transactions
	public List<Transactions> getAllTransactions();
	
	//This is to get List of user transactions
	public List<Transactions> getByUser(Accounts user);
	
	//This is to delete Transactions
	public void deleteTransaction(Transactions t);
}
