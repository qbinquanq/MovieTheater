package com.revature.dao;

import java.util.List;

import com.revature.beans.WalkIn;

public interface WalkInDao extends HibernateSession {
	//This is for inserting WalkIn information
	public Integer saveWalkIn(WalkIn walk);
	
	//This is for getting all WalkIns
	public List<WalkIn> getWalkIn();
}
