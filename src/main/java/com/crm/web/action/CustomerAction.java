package com.crm.web.action;

import com.crm.domain.Customer;
import com.crm.domain.Dict;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;

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

		//拼接查询的条件
		String cust_name = customer.getCust_name();
		if(cust_name != null && !cust_name.trim().isEmpty()){
			//说明，客户的名称输入值了
			criteria.add(Restrictions.like("cust_name","%"+cust_name+"%"));
		}
		//拼接客户的儿级别
		Dict level = customer.getLevel();
		if(level != null && !level.getDict_id().trim().isEmpty()){
			//说明，客户的级别肯定选择了一个级别
			criteria.add(Restrictions.eq("level.dict_id",level.getDict_id()));
		}
		//客户的来源
		Dict source=customer.getSource();
		if(source != null && !source.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("source.dict_id",source.getDict_id()));
		}

		//查询
		PageBean<Customer> page=customerService.findByPage(pageCode,pageSize,criteria);
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		//栈顶是map<"page",page对象>
		vs.set("page",page);
		return "page";
	}

	/**
	 * 初始化到添加的页面
	 * */
	public String initAddUI(){
		return "initAddUI";
	}

	/**
	 * 文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的
	 * private File upload;   //表示要上传的文件
	 * private String uploadFileName;   //表示上传文件的名称(没有中文乱码)
	 * private String uploadContentType;   //表示上传文件的mime类型
	 * 提供set方法，拦截器就注入值了
	 * */

	//要上传的文件
	private File upload;
	//文件的名称
	private String uploadFileName;
	//文件的mime类型
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 保存客户的方法
	 * @return
	 */
	public String save(){
		//做文件的上传
		if(uploadFileName != null){
			//打印
			System.out.println("文件名称；"+uploadFileName);
			System.out.println("文件类型；"+uploadContentType);
		}


		//保存客户成功了
		customerService.save(customer);

		return "save";
	}

}












