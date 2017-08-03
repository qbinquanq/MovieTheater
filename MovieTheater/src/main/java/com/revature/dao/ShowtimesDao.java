package com.revature.dao;

import java.util.List;

import com.revature.beans.Showtimes;

public interface ShowtimesDao {
	//This is to get all Showtimes available
	public List<Showtimes> getAllShow();
}
