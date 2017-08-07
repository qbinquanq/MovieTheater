package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.MovieInfo;
import com.revature.dao.MovieInfoDao;

@Repository
public class MovieInfoServiceImpl implements MovieInfoService {

	@Autowired
	private MovieInfoDao mid;

	public MovieInfoDao getMid() {
		return mid;
	}

	public void setMid(MovieInfoDao mid) {
		this.mid = mid;
	}

	@Override
	public List<MovieInfo> getAllMovieInfo() {
		return mid.getAllMovieInfo();
	}

	@Override
	public Integer saveMovieInfo(MovieInfo movieInfo) {
		return mid.saveMovieInfo(movieInfo);
	}
}
