package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Accounts;
import com.revature.beans.MovieInfo;
import com.revature.service.MovieInfoService;


@Controller
public class ManagerController {

	
	@Autowired
	private MovieInfoService us;
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/manager"}, method=RequestMethod.GET)
	public String getHomepage(HttpSession session)
	{
		if(session.getAttribute("user")!=null){
            return "static/manager.html";
        }
        return "redirect:index";
				
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
