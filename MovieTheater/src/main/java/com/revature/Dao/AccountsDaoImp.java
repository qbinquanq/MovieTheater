package com.revature.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Accounts;
import com.revature.util.HibernateUtil;

public class AccountsDaoImp implements AccountsDao{
  private static Logger log= Logger.getRootLogger();
  private static HibernateUtil hu= HibernateUtil.getInstance();
  public List<Accounts> getAll(){
	  Session session= hu.getSession();
	  String hql= "From com.revature.beans.Accounts";
	  List<Accounts> accounts = (List<Accounts>)session.createQuery(hql).list();
	  session.close();
	  
	  
	return accounts;
	  
  }
@Override
public Accounts login(String uname, String pword) {
	  
      Session session= hu.getSession();
      Transaction tx= session.beginTransaction();
      String hql= ("from Accounts where uname =:uname and pword =:pword");
      Query query= session.createQuery(hql);
      query.setString("uname", uname);
      query.setString("pword", pword);
       List userinfo = query.list();
       if(userinfo!=null && userinfo.size()>0){
            return (Accounts) userinfo.get(0);
       }
       else
    	   return null;
}

@Override
public Accounts save(Accounts accounts) {
	Session session = hu.getSession();
	Transaction tx = session.beginTransaction();
	int i = (Integer) session.save(accounts);
	log.warn("The user should have an id of " + i);
	log.warn("The user is: " + accounts);
	tx.commit();
	session.close();
	return accounts;
}

@Override
public void updateAccounts() {
	// HQL

	Session s = hu.getSession();
	Transaction tx = s.beginTransaction();
	String hql = "UPDATE Accounts SET userId=:'11' WHERE userId=:'5'";
	Query q = (Query) s.createQuery(hql);
	q.setParameter( "smith", 11);
	//q.setParameter("number", 5);
	log.warn("Number of rows affected by update: "+q.executeUpdate());
	tx.commit();
	s.close();
}


  
  
}
