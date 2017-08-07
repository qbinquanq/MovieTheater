package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Accounts;
import com.revature.service.LoginService;

@Controller
public class LoginController {
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
		return "redirect:index.html";
	}
	
	@RequestMapping(value="/loginthrough",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus login(String login, HttpSession session) throws JsonParseException, JsonMappingException, IOException
	{	
		
		Accounts accounts = om.readValue(login, Accounts.class);
		System.out.println("username :" + accounts.getUname());
		System.out.println("password :"+ accounts.getPword());
		Accounts  acc = ls.login(accounts.getUname(), accounts.getPword());
		if(acc==null){
			System.out.println("loginthrough page is null");
			//throw new ResourceNotFoundException(); 
			//return "static/index.html";
			//return loginFail();
			return HttpStatus.BAD_REQUEST;
		}
		else
		{
			System.out.println("login goes through");
			session.setAttribute("user", acc);
			//return "redirect:home";
			//return loginSuccess();
			//return "static/home.html";
			return HttpStatus.OK;
		}
	}
	
}
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6405897690634892881L;
    
}
