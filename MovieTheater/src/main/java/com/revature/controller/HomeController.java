package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Halls;
import com.revature.beans.Movies;
import com.revature.beans.Showtimes;
import com.revature.service.HallsService;
import com.revature.service.ShowtimesService;
import com.revature.testservices.HomeService;

@Controller
public class HomeController {
	
	@Autowired
	private HomeService hs;
	private ShowtimesService ss;
	private HallsService hll;
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/home"}, method=RequestMethod.GET)
	public String getSearchPage(HttpSession session)
	{
		return "static/home.html";
	}

	
	@RequestMapping(value="home/all", method=RequestMethod.POST)
	@ResponseBody
	public String getAll(HttpSession session) throws JsonProcessingException
	{
		List<Movies> m = hs.getAllMovies();
		System.out.println(om.writeValueAsString(m));
			return om.writeValueAsString(m);
	}

	@RequestMapping(value="info/movies",method=RequestMethod.POST)
	@ResponseBody
	public String displayAll(HttpSession session) throws JsonProcessingException
	{
		List<Showtimes> s = ss.getAllShow();
		System.out.println(om.writeValueAsString(s));
		return om.writeValueAsString(s);
	}

	public HomeService getHs() {
		return hs;
	}


	public void setHs(HomeService hs) {
		this.hs = hs;
	}




	public ShowtimesService getSs() {
		return ss;
	}


	public void setSs(ShowtimesService ss) {
		this.ss = ss;
	}

	

	public HallsService getHll() {
		return hll;
	}


	public void setHll(HallsService hll) {
		this.hll = hll;
	}



}
