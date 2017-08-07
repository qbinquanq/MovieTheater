package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		System.out.println("index page");
		if(session.getAttribute("user")!=null)
		{
				return "redirect:home";
			}
			//System.out.println(ls.login("rmiller", "mypassword"));
			return "static/index.html";
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addAccount(String account) throws JsonParseException, JsonMappingException, IOException{
		System.out.println(account);
		Accounts accounts = om.readValue(account, Accounts.class);
		System.out.println(accounts);
		Integer acc = as.saveUser(accounts);
		System.out.println("register "+acc);
		
		//System.out.println(u_firstname + " " + u_lastname + " " + user+ " " + email+ " "+ pass);
		//return "redirect:/index";
		if(acc == null){
			//return "Your username is not valid, please choose another one";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		//return "Congratulation! You successfully created a new account";
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
	public AccountService getAs(){
		return as;
	}
	public void setAs(AccountService as){
		this.as = as;
	}
}
