package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.beans.Accounts;
import com.revature.services.LoginService;

@Controller
@RequestMapping(value="/login")

public class TestLogin {
	@Autowired
	LoginService ls;
	
	public LoginService getLs() {
		return ls;
	}

	public void setLs(LoginService ls) {
		this.ls = ls;
	}

	//This maps to /login/hi
	//@RequestMapping(value="hi")
	//public void test(){System.out.println("hi");}
	
	@RequestMapping(method=RequestMethod.GET)
	public String goLogin(HttpSession session){
		if(session.getAttribute("user")!=null)
	{
			return "index";
		}
		//System.out.println(ls.login("rmiller", "mypassword"));
		return "static/login.html";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username, String password, HttpSession session)
	{	
		System.out.println(username);
		System.out.println(password);
		Accounts  acc = ls.login(username, password);
		if(acc==null)
			return "redirect:login";
		else
		{
			session.setAttribute("user", acc);
			return "redirect:home";
		}
	}

	
	

}
