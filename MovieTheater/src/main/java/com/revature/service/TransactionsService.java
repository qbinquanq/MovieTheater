package com.revature.service;

import java.util.List;

import com.revature.beans.Accounts;
import com.revature.beans.Transactions;

public interface TransactionsService {
	public Integer saveTransaction(Transactions transaction, int amt);

	public List<Transactions> getAllTransactions();

	public List<Transactions> getByUser(Accounts user);
	
	public void deleteTransaction(Transactions t);
}
