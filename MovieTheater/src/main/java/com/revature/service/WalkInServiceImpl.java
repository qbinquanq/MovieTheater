package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.WalkIn;
import com.revature.dao.WalkInDao;

@Repository
public class WalkInServiceImpl implements WalkInService{

	@Autowired
	private WalkInDao wid;
	
	public WalkInDao getWid(){
		return wid;
	}
	
	public void setWid(WalkInDao wid){
		this.wid = wid;
	}

	@Override
	public Integer saveWalkIn(WalkIn walk) {
		return wid.saveWalkIn(walk);
	}

	@Override
	public List<WalkIn> getWalkIn() {
		return wid.getWalkIn();
	}
}
