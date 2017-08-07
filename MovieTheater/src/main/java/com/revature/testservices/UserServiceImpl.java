package com.revature.testservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.MovieInfo;
import com.revature.dao.MovieInfoDao;

@Repository
public class UserServiceImpl implements UserService{

	@Autowired
	private MovieInfoDao mid;
	@Override
	public List<MovieInfo> getAllMovieInfo() {
		return null;
	}
	public MovieInfoDao getMid() {
		return mid;
	}
	public void setMid(MovieInfoDao mid) {
		this.mid = mid;
	}

	
	
}
