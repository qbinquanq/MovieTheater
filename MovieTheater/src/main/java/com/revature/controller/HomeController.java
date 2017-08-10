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
import com.revature.beans.Halls;
import com.revature.beans.MovieInfo;
import com.revature.beans.Movies;
import com.revature.beans.Showtimes;
import com.revature.service.HallsService;
import com.revature.service.MovieInfoService;
import com.revature.service.MovieService;
import com.revature.service.ShowtimesService;

@Controller
public class HomeController {
	
	@Autowired
	private MovieService ms;
	@Autowired
	private ShowtimesService ss;
	@Autowired
	private HallsService hll;
	
	@Autowired
	private MovieInfoService mi;
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value={"/home"}, method=RequestMethod.GET)
	public String getSearchPage(HttpSession session)
	{
		if(session.getAttribute("user")!=null)
		{
				return "static/home.html";
			}
			//System.out.println(ls.login("rmiller", "mypassword"));
			return "redirect:index";
	}
	@RequestMapping(value={"/home"}, method=RequestMethod.POST)
	public String navigateToHomePage(HttpSession session)
	{
		if(session.getAttribute("user")!=null)
		{
				return "redirect:home";
			}
			//System.out.println(ls.login("rmiller", "mypassword"));
			return "static/index.html";
	}
	
	@RequestMapping(value="home/all", method=RequestMethod.GET)
	@ResponseBody
	public String getAll(HttpSession session) throws JsonProcessingException
	{
		List<Movies> m = ms.getAllMovies();
		System.out.println(om.writeValueAsString(m));
			return om.writeValueAsString(m);
	}

	@RequestMapping(value="info/movies",method=RequestMethod.GET)
	@ResponseBody
	public String displayAll(HttpSession session) throws JsonProcessingException
	{
		List<Showtimes> s = ss.getAllShow();
		System.out.println(om.writeValueAsString(s));
		return om.writeValueAsString(s);
	}

	@RequestMapping(value="info/halls",method=RequestMethod.GET)
	@ResponseBody
	public String displayHall(HttpSession session) throws JsonProcessingException
	{
		List<Halls> h = hll.getHalls();
		System.out.println(om.writeValueAsString(h));
		return om.writeValueAsString(h);
	}

	
	@RequestMapping(value="movieinfo/all",method=RequestMethod.GET)
    @ResponseBody
    public String displayMovieInfo(HttpSession session) throws JsonProcessingException
    {
        List<MovieInfo> listMovie = mi.getAllMovieInfo();
        System.out.println(om.writeValueAsString(listMovie));
        return om.writeValueAsString(listMovie);
    }
	
	
	public ShowtimesService getSs() {
		return ss;
	}


	public void setSs(ShowtimesService ss) {
		this.ss = ss;
	}

	

	public HallsService getHll() {
		return hll;
	}


	public void setHll(HallsService hll) {
		this.hll = hll;
	}
	
	public MovieInfoService getMi() {
		return mi;
	}


	public void setMovieInfoService(MovieInfoService mi) {
		this.mi = mi;
	}

	public MovieService getMs() {
		return ms;
	}


	public void setMovieService(MovieService ms) {
		this.ms = ms;
	}

}
