package com.revature.driver;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.AccountsDao;
//import com.revature.dao.AccountsDaoImp;
import com.revature.beans.Accounts;
import com.revature.util.HibernateUtil;

public class Driver {

	private static Logger log = Logger.getRootLogger();
	private static HibernateUtil hu = HibernateUtil.getInstance();
	//private static AccountsDao accountsdao = new AccountsDaoImp();

	public static void main(String[] args) {
		// getAccounts();
		// accountsdao.updateAccounts();
		// System.out.print(accountsdao.login("john","pasword"));
		//insert();
		hu.getSessionFactory().close();
	}

	public static void getAccounts() {
		//System.out.print(accountsdao.getAll());
	}

	public static void insert() {
		Session session = hu.getSession();
		Transaction tx = session.beginTransaction();
		Accounts accounts = new Accounts(15, "aaaaa", "password", "aaaa@yahoo.com", "aaaaa", "bbbbbb");
		session.save(accounts);
		tx.commit();
		System.out.println("values going to insert" + accounts);
		session.close();

	}

}
