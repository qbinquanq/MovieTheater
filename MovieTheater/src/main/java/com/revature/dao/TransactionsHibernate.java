package com.revature.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Transactions;
import com.revature.util.HibernateUtil;

import oracle.sql.DATE;

public class TransactionsHibernate implements TransactionsDao {
	private HibernateUtil hu = HibernateUtil.getInstance();
	private Logger log = Logger.getRootLogger();

	// This is internal checking on auto Refund
	private void checkAuto(List<Transactions> allTrans) {
		for (Transactions trans : allTrans) {
			Calendar mov = Calendar.getInstance();
			mov.setTime(trans.getMovieInfo().getShowtime().getShowtime());
			Calendar cur = Calendar.getInstance();
			try {
				cur.setTime(DATE.getCurrentDate().dateValue());
			} catch (SQLException e) {
				log.trace(e.getMessage());
			}
			if (trans.getRequestRet() == 1 && (mov.get(Calendar.DAY_OF_YEAR) - cur.get(Calendar.DAY_OF_YEAR)) < 3) {
				deleteTransaction(trans);
			}
		}
	}

	@Override
	public Transactions saveTransaction(Transactions transaction, int amt) {
		for (int i = 0; i < amt; i++) {
			Session session = hu.getSession();
			Transaction tx = session.beginTransaction();
			session.save(transaction);
			log.warn("Transaction saved was: " + transaction);
			tx.commit();
			session.close();
		}
		return transaction;
	}

	@Override
	public List<Transactions> getAllTransactions() {
		Session s = hu.getSession();
		String hql = "From com.revature.beans.Transactions";
		List<Transactions> allTrans = (List<Transactions>) s.createQuery(hql).list();
		s.close();
		checkAuto(allTrans);
		return allTrans;
	}

	@Override
	public void deleteTransaction(Transactions t) {
		Session s = hu.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(t);
		tx.commit();
		s.close();
	}

}
