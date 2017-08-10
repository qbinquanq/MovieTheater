package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.MovieInfo;
import com.revature.beans.Transactions;
import com.revature.service.MovieInfoService;
import com.revature.service.TransactionsService;

@Controller
public class UserController {

	@Autowired
	private MovieInfoService us;

	@Autowired
	private TransactionsService ts;
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
	
	@RequestMapping(value="transactions/all",method=RequestMethod.POST)
	@ResponseBody
	public String getAllTrans(HttpSession session) throws JsonProcessingException
	{
		List<Transactions> trans = ts.getAllTransactions();

		
		return(om.writeValueAsString(trans));
	}

	
	public TransactionsService getTs() {
		return ts;
	}

	public void setTs(TransactionsService ts) {
		this.ts = ts;
	}

	public MovieInfoService getUs() {
		return us;
	}

	public void setUs(MovieInfoService us) {
		this.us = us;
	}
	
}
