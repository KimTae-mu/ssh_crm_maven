package com.crm.web.action;

/**
 * Created by Taeyeon-Serenity on 2017/8/22.
 */

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action的父类
 * */
public class BaseAction extends ActionSupport {

    //属性驱动的方法
    //当前页，默认值就是1
    private Integer pageCode=1;


    public void setPageCode(Integer pageCode) {
        if(pageCode == null){
            pageCode=1;
        }
        this.pageCode = pageCode;
    }
    //每页显示数据的条数
    private Integer pageSize=2;
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCode() {
        return pageCode;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 调用值栈对象的set方法
     * */
    public void setVs(String key,Object obj){
        ActionContext.getContext().getValueStack().set(key,obj);
    }

    /**
     * 调用值栈的push方法
     * */
    public void pushVs(Object obj){
        ActionContext.getContext().getValueStack().push(obj);
    }

}
