package com.revature.dao;

import java.util.List;

import com.revature.beans.Transactions;

public interface TransactionsDao {
	//This is to save Transations
	public Transactions saveTransaction(Transactions transaction, int amt);
	
	//This is to get a list of Transactions
	public List<Transactions> getAllTransactions();
	
	//This is to delete Transactions
	public void deleteTransaction(Transactions t);
}
