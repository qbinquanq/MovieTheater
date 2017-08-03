package com.revature.dao;

import java.util.List;

import com.revature.beans.MovieInfo;

public interface MovieInfoDao {
	//This is for getting all results from MovieInfo table
	public List<MovieInfo> getAllMovieInfo();
	
	//This is for inserting new MovieInfo(Movie, Hall, Showtime)
	public MovieInfo saveMovieInfo(MovieInfo movieInfo);	
}
