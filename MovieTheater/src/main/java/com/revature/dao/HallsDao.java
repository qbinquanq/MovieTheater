package com.revature.dao;

import java.util.List;

import com.revature.beans.Halls;

public interface HallsDao extends HibernateSession{
	//This is to get all Halls available
	public List<Halls> getHalls();
}
