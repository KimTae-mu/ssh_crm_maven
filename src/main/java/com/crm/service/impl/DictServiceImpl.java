package com.crm.service.impl;

import com.crm.dao.DictDao;
import com.crm.domain.Dict;
import com.crm.service.DictService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */
/**
 * 字典
 * */
@Transactional
public class DictServiceImpl implements DictService {
    private DictDao dictDao;

    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    /**
     * 根据客户类别编码查询字典
     *   */
    public List<Dict> findByCode(String dict_type_code) {
        return dictDao.findByCode(dict_type_code);
    }
}
