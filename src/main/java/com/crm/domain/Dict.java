package com.crm.domain;

/**
 * Created by Taeyeon-Serenity on 2017/8/9.
 */
/**
 * 字典表
 * */
public class Dict {
    /*
    *   `dict_id` varchar(32) NOT NULL COMMENT '数据字典id(主键)',
          `dict_type_code` varchar(10) NOT NULL COMMENT '数据字典类别代码',
          `dict_type_name` varchar(64) NOT NULL COMMENT '数据字典类别名称',
          `dict_item_name` varchar(64) NOT NULL COMMENT '数据字典项目名称',
          `dict_item_code` varchar(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
          `dict_sort` int(10) DEFAULT NULL COMMENT '排序字段',
          `dict_enable` char(1) NOT NULL COMMENT '1:使用 0:停用',
          `dict_memo` varchar(64) DEFAULT NULL COMMENT '备注',
  */

    private String dict_id;
    //数据字典类别代码 01 06
    private String dict_type_code;
    //类别名称  01所属行业 06客户级别
    private String dict_type_name;

}
