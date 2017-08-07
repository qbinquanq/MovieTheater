package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.WalkIn;
import com.revature.dao.WalkInDao;

@Component
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
}
