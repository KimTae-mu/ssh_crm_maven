package com.crm.dao.impl;

import com.crm.dao.VisitDao;
import com.crm.domain.Visit;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Taeyeon-Serenity on 2017/8/23.
 */
/**
 * 客户拜访的持久层
 * */
@Repository(value = "visitDao")
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao {

    @Resource(name = "sessionFactory")
    public void set2SessionFactory(SessionFactory sessionFactory){
        //关键，调用父类的方法
        super.setSessionFactory(sessionFactory);
    }
}
