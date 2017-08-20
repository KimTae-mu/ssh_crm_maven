package com.crm.dao;

import com.crm.domain.Dict;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */
public interface DictDao {
    public List<Dict> findByCode(String dict_type_code);
}
