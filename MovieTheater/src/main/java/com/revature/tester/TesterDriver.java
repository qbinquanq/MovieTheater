package com.revature.tester;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Movies;
import com.revature.dao.MovieDao;
import com.revature.dao.MovieHibernate;

public class TesterDriver {
	private static MovieDao md = new MovieHibernate();
	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		log.warn(md.getAllMovies());
		System.out.println(LocalDate.now().getDayOfWeek());
	}
}
