package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.Showtimes;
import com.revature.dao.ShowtimesDao;

@Component
public class ShowtimesServiceImpl implements ShowtimesService {

	@Autowired
	private ShowtimesDao sd;

	public ShowtimesDao getSd() {
		return sd;
	}

	public void setSd(ShowtimesDao sd) {
		this.sd = sd;
	}

	@Override
	public List<Showtimes> getAllShow() {
		return sd.getAllShow();
	}
}
