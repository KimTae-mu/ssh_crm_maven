package com.crm.web.action;

import com.crm.dao.impl.BaseDaoImpl;
import com.crm.domain.Customer;
import com.crm.domain.Linkman;
import com.crm.domain.PageBean;
import com.crm.service.LinkmanService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Taeyeon-Serenity on 2017/8/22.
 */
public class LinkmanAction extends BaseAction implements ModelDriven<Linkman> {

    private Linkman linkman=new Linkman();
    public Linkman getModel() {
        return linkman;
    }

    private LinkmanService linkmanService;

    public LinkmanService getLinkmanService() {
        return linkmanService;
    }

    public void setLinkmanService(LinkmanService linkmanService) {
        this.linkmanService = linkmanService;
    }

    /**
     * 分页查询
     * */
    public String findByPage(){
        DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
        //获取到联系人的名称
        String lkm_name = linkman.getLkm_name();
        if(lkm_name != null && !lkm_name.trim().isEmpty()){
            criteria.add(Restrictions.like("lkm_name","%"+lkm_name+"%"));
        }

        //获取客户
        Customer c = linkman.getCustomer();
        if(c != null && c.getCust_id() != null){
            //拼接查询的条件
            criteria.add(Restrictions.eq("customer.cust_id",c.getCust_id()));
        }

        //调用业务层
        PageBean<Linkman> page=linkmanService.findByPage(this.getPageCode(),this.getPageSize(),criteria);

        //压栈
        this.setVs("page",page);

        return "page";
    }

    /**
     * 初始化到添加的页面
     * */
    public String initAddUI(){
        return "initAddUI";
    }
}
