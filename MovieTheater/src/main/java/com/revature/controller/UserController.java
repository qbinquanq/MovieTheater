package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Accounts;
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
	
	//display all movies
	@RequestMapping(value="movie/all", method=RequestMethod.POST)
	@ResponseBody
	public String getAll() throws JsonProcessingException
	{
		List<MovieInfo> mi = us.getAllMovieInfo();
		System.out.println(om.writeValueAsString(mi));
			return om.writeValueAsString(mi);
	}
	
	//display all transaction for specific user
	@RequestMapping(value="transactions/all",method=RequestMethod.POST)
	@ResponseBody
	public String getAllTrans(String account, HttpSession session) throws JsonParseException, JsonMappingException, IOException
	{
		List<Transactions> trans = ts.getAllTransactions();
		List<Transactions> user = new ArrayList<Transactions>();
		for(Transactions x: trans){
			if(x.getAccount().equals((Accounts)session.getAttribute("user"))){
				user.add(x);
				System.out.println(user);
			}
		}
		
		return(om.writeValueAsString(user));
	}

	//delete a transaction
	@RequestMapping(value="/trans/remove",method=RequestMethod.POST)
	@ResponseBody
	public String removeTrans(Transactions trans){
		ts.deleteTransaction(trans);
		return "/static/user.html";
	}
	
	//apply for a refund
	@RequestMapping(value="/applyRefund/{ref}/{traId}", method=RequestMethod.POST)
	@ResponseBody
	public String applyRefund(@PathVariable int ref, @PathVariable int traId, HttpSession session) throws JsonParseException, JsonMappingException, IOException{
		Transactions transaction = ts.getTransById(traId);
		transaction.setRequestRet(ref);
		ts.updateTrans(transaction);

		return "static/user.html";
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
