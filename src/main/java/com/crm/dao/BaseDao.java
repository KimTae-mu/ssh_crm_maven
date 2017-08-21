package com.crm.dao;

/**
 * Created by Taeyeon-Serenity on 2017/8/21.
 */

import com.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 以后所有的dao的接口都需要继承BaseDao接口
 * */
public interface BaseDao<T> {

    public void save(T t);

    public void delete(T t);

    public void update(T t);

    public T findById(Long id);

    public List<T> findAll();

    public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
