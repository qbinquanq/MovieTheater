package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.Movies;
import com.revature.dao.MovieDao;

@Component
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao md;

	public MovieDao getMd() {
		return md;
	}

	public void setMd(MovieDao md) {
		this.md = md;
	}

	@Override
	public List<Movies> getAllMovies(Movies movies) {
		return md.getAllMovies();
	}
}
