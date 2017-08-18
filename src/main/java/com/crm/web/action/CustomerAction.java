package com.crm.web.action;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 客户的控制层
 * @author Administrator
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	private static final long serialVersionUID = 113695314694166436L;
	
	// 不要忘记手动new
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	// 提供service的成员属性，提供set方法
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/**
	 * 保存客户的方法
	 * @return
	 */
	public String add(){
		customerService.save(customer);
		
		return NONE;
	}

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

	/**
	 * 分页查询的方法
	 * */
	public String findByPage(){
		//调用业务层
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//查询
		PageBean<Customer> page=customerService.findByPage(pageCode,pageSize,criteria);
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		//栈顶是map<"page",page对象>
		vs.set("page",page);
		return "page";
	}

}












