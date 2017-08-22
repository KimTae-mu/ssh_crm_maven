package com.crm.service.impl;

import com.crm.dao.LinkmanDao;
import com.crm.domain.Linkman;
import com.crm.domain.PageBean;
import com.crm.service.LinkmanService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Taeyeon-Serenity on 2017/8/22.
 */
@Transactional
public class LinkmanServiceImpl implements LinkmanService {

    private LinkmanDao linkmanDao;

    public void setLinkmanDao(LinkmanDao linkmanDao) {
        this.linkmanDao = linkmanDao;
    }

    public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return linkmanDao.findByPage(pageCode,pageSize,criteria);
    }
}
