package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Accounts;
import com.revature.beans.MovieInfo;
import com.revature.beans.WalkIn;
import com.revature.service.MovieInfoService;
import com.revature.service.WalkInService;

@Controller
public class EmployeeController {
	@Autowired
	private WalkInService wis;
	@Autowired
	private MovieInfoService mis;
	private ObjectMapper om = new ObjectMapper();

	public WalkInService getWis() {
		return wis;
	}

	public void setWis(WalkInService wis) {
		this.wis = wis;
	}

	public MovieInfoService getMis() {
		return mis;
	}

	public void setMis(MovieInfoService mis) {
		this.mis = mis;
	}

	@RequestMapping(value = { "/employee" }, method = RequestMethod.GET)
	public String navigateToEmployee(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "static/employee.html";
		}
		return "redirect:index";
	}

	@RequestMapping(value = { "/employee" }, method = RequestMethod.POST)
	public String navigateToHomePage(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "static/employee.html";
		}
		return "redirect:index";
	}

	@RequestMapping(value = "employee/movieinfo", method = RequestMethod.POST)
	@ResponseBody
	public String getAll(HttpSession session) throws JsonProcessingException {
		List<MovieInfo> mil = mis.getAllMovieInfo();
		System.out.println(om.writeValueAsString(mil));
		return om.writeValueAsString(mil);
	}

	@RequestMapping(value = "employee/walkin", method = RequestMethod.POST)
	@ResponseBody
	public String getWalk(HttpSession session) throws JsonProcessingException {
		List<WalkIn> wil = wis.getWalkIn();
		System.out.println(om.writeValueAsString(wil));
		return om.writeValueAsString(wil);
	}
	
	@RequestMapping(value="employee/{amt}/{info}", method = RequestMethod.POST)
	@ResponseBody
	public String insertWalkIn(@PathVariable Integer amt, @PathVariable Integer info, HttpSession session){
		WalkIn wi = new WalkIn();
		MovieInfo mi = mis.getInfoById(info);
		mi.setWalkTot((amt+mi.getOnlineTot()+mi.getWalkTot()));
		wi.setAccount((Accounts)session.getAttribute("user"));
		wi.setMovieInfo(mi);
		wi.setWalkAmount(amt);
		wis.saveWalkIn(wi);
		mis.updateInfo(mi);
		return "static/employee.html";
	}
}