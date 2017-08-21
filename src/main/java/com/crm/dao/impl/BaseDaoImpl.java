package com.crm.dao.impl;

import com.crm.dao.BaseDao;
import com.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.print.attribute.standard.PagesPerMinute;
import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/8/21.
 */
/**
 * 以后所有的Dao层的实现类，都可以继承BaseDaoImpl，增删改查分页方法不用再编写了*/
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class clazz;

    /**
     * 保存
     * */
    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    /**
     * 删除
     * */
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    /**
     * 修改
     * */
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    /**
     * 通过主键查询
     * */
    public T findById(Long id) {
        return (T) this.getHibernateTemplate().get(clazz,id);
    }

    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
    }

    /**
     * 分页
     * */
    public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        //创建分页的对象
        PageBean<T> page=new PageBean<T>();
        //一个一个的设置
        page.setPageCode(pageCode);
        page.setPageSize(pageSize);

        criteria.setProjection(Projections.rowCount());
        List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
        if(list != null && list.size() > 0){
            int totalCount = list.get(0).intValue();
            //总记录数
            page.setTotalCount(totalCount);
        }

        //清除Sql
        criteria.setProjection(null);

        List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);

        //每页显示的数据
        page.setBeanList(beanList);

        return page;
    }
}
