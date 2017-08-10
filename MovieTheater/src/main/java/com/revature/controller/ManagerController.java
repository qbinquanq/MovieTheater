package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.MovieInfo;
import com.revature.beans.Transactions;
import com.revature.service.MovieInfoService;
import com.revature.service.TransactionsService;


@Controller
public class ManagerController {

	
	@Autowired
	private MovieInfoService us;
	@Autowired
	private TransactionsService ts;
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/manager"}, method=RequestMethod.GET)
	public String getHomepage(HttpSession session)
	{
		if(session.getAttribute("user")!=null){
            return "static/manager.html";
        }
        return "redirect:index";
				
	}
	
	@RequestMapping(value={"/refund/all"}, method=RequestMethod.POST)
	@ResponseBody
	public String getRefund(HttpSession session) throws JsonProcessingException
	{
		
		List<Transactions> trans = ts.getAllTransactions();
		System.out.println(om.writeValueAsString(trans));
		return om.writeValueAsString(trans);
	}
	
	@RequestMapping(value={"save/hall"},method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity halls(String halls,HttpSession session) throws JsonParseException, JsonMappingException, IOException
	{
		MovieInfo movieinfo = om.readValue(halls,MovieInfo.class );
		System.out.println("updated Halls");
		Integer info = us.saveMovieInfo(movieinfo);
		System.out.println(info);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}





@RequestMapping(value="manager/movieinfo", method=RequestMethod.POST)
@ResponseBody
public String getAll(HttpSession session) throws JsonProcessingException
{
    
	List<MovieInfo> mil = us.getAllMovieInfo();
    System.out.println(om.writeValueAsString(mil));
        return om.writeValueAsString(mil);
}
}

