package com.crm.service;

import java.util.List;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface CustomerService {
	
	public void save(Customer customer);


    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

    public Customer findById(Long cust_id);

    public void delete(Customer customer);

    public void update(Customer customer);
}
