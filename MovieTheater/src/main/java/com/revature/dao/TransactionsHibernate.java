package com.revature.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Transactions;

public class TransactionsHibernate implements TransactionsDao, HibernateSession {
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
	public Transactions saveTransaction(Transactions transaction, int amt) {
		for (int i = 0; i < amt; i++) {
			Transaction tx = session.beginTransaction();
			session.save(transaction);
			tx.commit();
		}
		return transaction;
	}

	@Override
	public List<Transactions> getAllTransactions() {
		String hql = "From com.revature.beans.Transactions";
		List<Transactions> allTrans = (List<Transactions>) session.createQuery(hql).list();
		checkAuto(allTrans);
		return allTrans;
	}

	@Override
	public void deleteTransaction(Transactions t) {
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

}
