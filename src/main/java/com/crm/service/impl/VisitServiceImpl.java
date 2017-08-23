package com.crm.service.impl;

import com.crm.dao.VisitDao;
import com.crm.domain.PageBean;
import com.crm.domain.Visit;
import com.crm.service.VisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Taeyeon-Serenity on 2017/8/23.
 */
/**
 * 客户拜访业务层
 * */
@Service(value = "visitService")
@Transactional
public class VisitServiceImpl implements VisitService {

    @Resource(name = "visitDao")
    private VisitDao visitDao;

    /**
     * 分页查询
     * */
    public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return visitDao.findByPage(pageCode,pageSize,criteria);
    }

    public void save(Visit visit) {
        visitDao.save(visit);
    }
}
