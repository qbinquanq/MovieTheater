package com.revature.dao;

import com.revature.beans.WalkIn;

public interface WalkInDao extends HibernateSession {
	//This is for inserting WalkIn information
	public WalkIn saveWalkIn(WalkIn walk);
}
