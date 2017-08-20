package com.crm.dao.impl;

import com.crm.dao.DictDao;
import com.crm.domain.Dict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

    /**
     * 通过客户类别编码查询字典
     * */
    public List<Dict> findByCode(String dict_type_code) {
        return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?",dict_type_code);
    }
}
