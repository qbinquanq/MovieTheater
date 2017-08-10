package com.revature.service;

import java.util.List;

import com.revature.beans.MovieInfo;

public interface MovieInfoService {
	public List<MovieInfo> getAllMovieInfo();

	public Integer saveMovieInfo(MovieInfo movieInfo);
	
	public MovieInfo getInfoById(int infoId);
	
	public MovieInfo updateInfo(MovieInfo mi);
}
