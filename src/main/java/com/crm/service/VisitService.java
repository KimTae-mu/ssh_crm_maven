package com.crm.service;

import com.crm.domain.PageBean;
import com.crm.domain.Visit;
import org.hibernate.criterion.DetachedCriteria;

/**
 * Created by Taeyeon-Serenity on 2017/8/23.
 */
public interface VisitService {
    public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

    public void save(Visit visit);
}
