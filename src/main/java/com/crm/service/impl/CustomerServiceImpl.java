package com.crm.service.impl;

import java.util.List;

import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;

/**
 * 客户的业务层
 * @author Administrator
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	/**
	 * 分页查询
	 * */
    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return customerDao.findByPage(pageCode,pageSize,criteria);
    }

    /**
	 * 通过主键，查询客户
	 * */
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    /**
	 * 删除客户
	 * */
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	/**
	 * 修改客户
	 * */
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	/**
	 * 查询所有客户
	 * */
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

}
