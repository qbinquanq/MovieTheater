package com.revature.dao;

import java.util.List;

import com.revature.beans.MovieInfo;

public interface MovieInfoDao extends HibernateSession {
	// This is for getting all results from MovieInfo table
	public List<MovieInfo> getAllMovieInfo();

	// This is for inserting new MovieInfo(Movie, Hall, Showtime)
	public Integer saveMovieInfo(MovieInfo movieInfo);
	
	//This is for getting Movie info by ID
	public MovieInfo getInfoById(int infoId);
}
