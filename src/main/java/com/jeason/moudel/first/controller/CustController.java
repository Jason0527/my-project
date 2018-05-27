package com.jeason.moudel.first.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeason.moudel.first.entity.Cust;
import com.jeason.moudel.first.service.CustService;

@Controller
@RequestMapping("cust")
public class CustController {
	@Autowired
	CustService service;
	@RequestMapping("login")
	public String login(Cust cust){
		System.out.println("login");
		List<Cust> list = service.findList(cust);
		if(list != null && list.size()>0){
			return "index";
		}else{
			return "error";
		}
	}
	@RequestMapping("toLogin")
	public String toLogin(){
		System.out.println("toLoing");
		return "login";
	}
	@RequestMapping("testCache")
	@ResponseBody
	public Map<String,Object> testCache(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		System.out.println("------testCache------");
		String custNo = request.getParameter("custNo");
		Cust cust = new Cust();
		cust.setCustNo(custNo);
		List<Cust> list = service.findList(cust);
		result.put("retCode","0");
		result.put("data",list.get(0));
		return result;
	}
}
