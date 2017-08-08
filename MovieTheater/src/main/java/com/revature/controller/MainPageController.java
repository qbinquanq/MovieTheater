package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Accounts;

@Controller
public class MainPageController {
	
	@RequestMapping(value="/mainpage", method=RequestMethod.GET)
	@ResponseBody
	public String goMainPage(HttpSession session){
		if(session.getAttribute("user")!=null)
		{
			Accounts acc = (Accounts) session.getAttribute("user");
			System.out.println(acc);
			if(acc.getUserId() > 4)
				return "user.html";
			else if(acc.getUserId() == 1)
				return "manager.html";
			return "employee.html";
		}
		//System.out.println(ls.login("rmiller", "mypassword"));
		return "redirect:index";
	}
	
}
