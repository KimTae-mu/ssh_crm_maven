package com.crm.dao;

import java.util.List;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface CustomerDao {
	
	public void save(Customer customer);

    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
