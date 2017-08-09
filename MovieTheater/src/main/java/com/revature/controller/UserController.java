package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.MovieInfo;
import com.revature.service.MovieInfoService;

@Controller
public class UserController {

	@Autowired
	private MovieInfoService us;

	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/user"}, method=RequestMethod.GET)
	public String getHomepage(HttpSession session)
	{
		if(session.getAttribute("user")!=null){
            return "static/user.html";
        }
        return "redirect:index";
	}
	
	@RequestMapping(value="movie/all", method=RequestMethod.POST)
	@ResponseBody
	public String getAll() throws JsonProcessingException
	{
		List<MovieInfo> mi = us.getAllMovieInfo();
		System.out.println(om.writeValueAsString(mi));
			return om.writeValueAsString(mi);
	}
	
	@RequestMapping(value="transactions/all",method=RequestMethod.GET)

	public MovieInfoService getUs() {
		return us;
	}

	public void setUs(MovieInfoService us) {
		this.us = us;
	}
	
}
