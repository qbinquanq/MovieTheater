package com.revature.dao;

import java.util.List;

import com.revature.beans.Showtimes;

public interface ShowtimesDao extends HibernateSession {
	//This is to get all Showtimes available
	public List<Showtimes> getAllShow();
}
