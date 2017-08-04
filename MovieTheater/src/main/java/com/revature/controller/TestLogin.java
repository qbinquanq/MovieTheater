package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Accounts;
import com.revature.services.LoginService;

@Controller
public class TestLogin {
	@Autowired
	LoginService ls;
	ObjectMapper om = new ObjectMapper();
	public LoginService getLs() {
		return ls;
	}

	public void setLs(LoginService ls) {
		this.ls = ls;
	}

	//This maps to /login/hi
	//@RequestMapping(value="hi")
	//public void test(){System.out.println("hi");}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goLogin(HttpSession session){
		if(session.getAttribute("user")!=null)
	{
			return "redirect:home";
		}
		//System.out.println(ls.login("rmiller", "mypassword"));
		return "static/login.html";
	}
	
	@RequestMapping(value="/loginthrough",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String login(String login, HttpSession session) throws JsonParseException, JsonMappingException, IOException
	{	
		
		Accounts accounts = om.readValue(login, Accounts.class);
		System.out.println("username :" + accounts.getUname());
		System.out.println("password :"+ accounts.getPword());
		Accounts  acc = ls.login(accounts.getUname(), accounts.getPword());
		if(acc==null)
			return "redirect:login";
		else
		{
			System.out.println("login is good");
			session.setAttribute("user", acc);
			return "redirect:home";
		}
	}

	
	

}
