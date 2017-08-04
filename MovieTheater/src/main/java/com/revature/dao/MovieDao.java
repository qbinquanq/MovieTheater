package com.revature.dao;

import java.util.List;

import com.revature.beans.Movies;

public interface MovieDao extends HibernateSession{
	//This is to get a list of all the movies
	public List<Movies> getAllMovies();
}
