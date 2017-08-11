package com.revature.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.revature.beans.Accounts;
import com.revature.beans.Transactions;

@Component
public class TransactionsHibernate implements TransactionsDao {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	// This is internal checking on auto Refund
	private void checkAuto(List<Transactions> allTrans) {
		for (Transactions trans : allTrans) {
			Calendar mov = Calendar.getInstance();
			mov.setTime(trans.getMovieInfo().getShowtime().getShowtime());
			Calendar cur = Calendar.getInstance();
			if (trans.getRequestRet() == 1 && (mov.get(Calendar.DAY_OF_YEAR) - cur.get(Calendar.DAY_OF_YEAR)) < 3) {
				deleteTransaction(trans);
			}
		}
	}

	@Override
	public Integer saveTransaction(Transactions transaction, int amt) {
		for (int i = 0; i < amt-1; i++) {
			session.save(transaction);
		}
		return (Integer) session.save(transaction);
	}

	@Override
	public List<Transactions> getAllTransactions() {
		return (List<Transactions>) session.createCriteria(Transactions.class).list();
	}

	@Override
	public void deleteTransaction(Transactions t) {
		session.delete(t);
	}

	@Override
	public List<Transactions> getByUser(Accounts user) {
		return (List<Transactions>) session.createCriteria(Transactions.class).add(Restrictions.eq("userId", user.getUserId())).list();
	}
}
