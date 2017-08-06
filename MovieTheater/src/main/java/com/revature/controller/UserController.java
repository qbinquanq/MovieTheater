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
import com.revature.beans.MovieInfo;
import com.revature.testservices.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/user"}, method=RequestMethod.GET)
	public String getHomepage(HttpSession session)
	{
		return "static/user.html";
	}
	
	@RequestMapping(value="movie/all", method=RequestMethod.POST)
	@ResponseBody
	public String getAll() throws JsonProcessingException
	{
		List<MovieInfo> mi = us.getAllMovieInfo();
		System.out.println(om.writeValueAsString(mi));
			return om.writeValueAsString(mi);
	}

	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}
	
	
}
