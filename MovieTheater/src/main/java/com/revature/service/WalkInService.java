package com.revature.service;

import java.util.List;

import com.revature.beans.WalkIn;

public interface WalkInService {
	public Integer saveWalkIn(WalkIn walk);
	public List<WalkIn> getWalkIn();
}
