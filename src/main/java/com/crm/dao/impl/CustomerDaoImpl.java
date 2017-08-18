package com.crm.dao.impl;

import java.util.List;

import com.crm.dao.CustomerDao;
import com.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
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

	/**
	 * 分页的查询
	 * */
    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
    	PageBean<Customer> page=new PageBean<Customer>();
    	page.setPageCode(pageCode);
    	page.setPageSize(pageSize);

    	//先查询总记录数	select count(*)
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			//总的记录数
			page.setTotalCount(totalCount);
		}

		//强调：把select count(*) 先清空
		criteria.setProjection(null);

		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);

		//分页查询数据，分页显示的数据	使用limit
		page.setBeanList(beanList);

        return page;
    }

}
