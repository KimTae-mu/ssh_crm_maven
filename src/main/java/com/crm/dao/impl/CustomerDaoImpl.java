package com.crm.dao.impl;

import java.util.List;

import com.crm.dao.CustomerDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.domain.Customer;

/**
 * 持久层
 * @author Administrator
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
	
	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		// 把数据，保存到数据库中
		this.getHibernateTemplate().save(customer);
	}

}
