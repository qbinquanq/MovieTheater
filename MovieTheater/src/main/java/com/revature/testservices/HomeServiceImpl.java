package com.revature.testservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Movies;
import com.revature.dao.MovieDao;

@Repository
public class HomeServiceImpl implements HomeService{

	@Autowired 
	private MovieDao md;
	@Override
	public List<Movies> getAllMovies() {
		return md.getAllMovies();
	}
	public MovieDao getMd() {
		return md;
	}
	public void setMd(MovieDao md) {
		this.md = md;
	}
	
	
}
