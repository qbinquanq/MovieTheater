package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Halls;
import com.revature.dao.HallsDao;

@Repository
public class HallsServiceImpl implements HallsService {

	@Autowired
	private HallsDao hd;

	public HallsDao getHd() {
		return hd;
	}

	public void setHd(HallsDao hd) {
		this.hd = hd;
	}

	@Override
	public List<Halls> getHalls() {
		return hd.getHalls();
	}
}
