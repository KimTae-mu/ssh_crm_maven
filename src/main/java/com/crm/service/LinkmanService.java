package com.crm.service;

import com.crm.domain.Linkman;
import com.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

/**
 * Created by Taeyeon-Serenity on 2017/8/22.
 */
public interface LinkmanService {
    public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
