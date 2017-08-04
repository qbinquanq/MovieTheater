package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Accounts;
import com.revature.service.AccountService;

@Controller
public class MovieController {
	
	@Autowired
	private AccountService as;
	
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String getHomepage(HttpSession session)
	{
		
			return "static/index.html";
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addAccount(String account) throws JsonParseException, JsonMappingException, IOException{
		System.out.println(account);
		Accounts accounts = om.readValue(account, Accounts.class);
		System.out.println(accounts);
		as.saveUser(accounts);
		//System.out.println(u_firstname + " " + u_lastname + " " + user+ " " + email+ " "+ pass);
		//return "redirect:/index";
	}
	
	
	public AccountService getAs(){
		return as;
	}
	public void setAs(AccountService as){
		this.as = as;
	}
}
