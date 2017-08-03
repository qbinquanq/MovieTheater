package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Accounts;

@Controller
public class MovieController {
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String getHomepage(HttpSession session)
	{
		
			return "static/index.html";
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.OK)
	public String returnBackToHome(String regis,HttpSession session) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("at register");
		Accounts account = om.readValue(regis, Accounts.class);
		System.out.println(account);
		//System.out.println(u_firstname + " " + u_lastname + " " + user+ " " + email+ " "+ pass);
		return "redirect:/index";
	}
}
