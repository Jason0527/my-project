package com.jeason.moudel.first.dao;

import java.util.List;



import com.jeason.moudel.first.entity.Cust;
public interface  CustDao {
	public List<Cust> findList(Cust cust);
}
