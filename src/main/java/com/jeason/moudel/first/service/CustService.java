package com.jeason.moudel.first.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jeason.moudel.first.dao.CustDao;
import com.jeason.moudel.first.entity.Cust;
@Service
public class CustService {
	@Autowired
	CustDao dao;
	@Cacheable(value="testCache",key="#cust.custNo")
	public List<Cust> findList(Cust cust){
		return dao.findList(cust);
	}
	
}
