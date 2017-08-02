package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getHomepage(HttpSession session)
	{
		
			return "static/index.html";
	}
}
