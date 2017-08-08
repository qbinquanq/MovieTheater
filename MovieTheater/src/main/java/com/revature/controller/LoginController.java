package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class LoginController {
	@Autowired
	AccountService ls;
	ObjectMapper om = new ObjectMapper();
	public AccountService getLs() {
		return ls;
	}

	public void setLs(AccountService ls) {
		this.ls = ls;
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goLogin(HttpSession session){
		if(session.getAttribute("user")!=null)
	{
			return "redirect:home";
		}
		return "redirect:index";
	}
	
	@RequestMapping(value="/loginthrough",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity login(String login, HttpSession session) throws JsonParseException, JsonMappingException, IOException
	{	
		
		Accounts accounts = om.readValue(login, Accounts.class);
		System.out.println("username :" + accounts.getUname());
		System.out.println("password :"+ accounts.getPword());
		Accounts  acc = ls.login(accounts.getUname(), accounts.getPword());
		if(acc==null){
			System.out.println("loginthrough page is null");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		else
		{
			System.out.println("login goes through");
			session.setAttribute("user", acc);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
}

