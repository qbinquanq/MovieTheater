package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Movies;

import testservices.HomeService;

@Controller
public class HomeController {
	
	/*@Autowired
	HomeService hs;
	private ObjectMapper om = new ObjectMapper();*/
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHomepage()
	{
		return "static/home.html";
	}

	
	/*@RequestMapping(value="/home/all", method=RequestMethod.GET, produces={"application/xml","application/json"})
	@ResponseBody
	public String getHomepage() throws JsonProcessingException
	{
		List<Movies> m = hs.getAllMovies();
		System.out.println(om.writeValueAsString(m));
			return om.writeValueAsString(m);
	}*/

	
}
