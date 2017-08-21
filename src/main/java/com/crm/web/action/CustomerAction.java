package com.crm.web.action;

import com.crm.domain.Customer;
import com.crm.domain.Dict;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;

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
	public String save() throws IOException {
		//做文件的上传
		if(uploadFileName != null){
			//打印
			System.out.println("文件类型；"+uploadContentType);
			//把文件的名称处理一下
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//把文件上传到tomcat服务器下面的upload文件夹中
			String path="E:\\apache-tomcat-7.0.73\\webapps\\upload\\";
			File file=new File(path+uuidName);
			//简单方式
			FileUtils.copyFile(upload,file);

			//把上传的文件的路径，保存到客户表中
			customer.setFilepath(path+uuidName);
		}


		//保存客户成功了
		customerService.save(customer);

		return "save";
	}

	/**
	 * 删除客户
	 * */
	public String delete(){
		//删除客户，获取客户的信息，获取到上传文件的路径
		customer=customerService.findById(customer.getCust_id());
		//获取上传文件的路径
		String filepath=customer.getFilepath();
		//删除客户
		customerService.delete(customer);

		//再删除文件
		File file=new File(filepath);
		if(file.exists()){
			file.delete();
		}

		return "delete";
	}

	/**
	 * 跳转到初始化修改的页面
	 * */
	public String initUpdate(){
		customer = customerService.findById(this.customer.getCust_id());
		return "initUpdate";
	}

	/**
	 * 修改客户
	 * */
	public String update() throws IOException {
		//判断	说明客户上传了新的图片
		if(uploadFileName != null){
			//先删除旧的图片
			String oldFilepath = customer.getFilepath();
			if(oldFilepath != null && !oldFilepath.trim().isEmpty()){
				//说明，旧的路径存在，需要删除图片
				File f=new File(oldFilepath);
				f.delete();
			}
			//上传新的图片
			//先处理文件的名称问题
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String path="E:\\apache-tomcat-7.0.73\\webapps\\upload\\";
			File file=new File(path+uuidName);
			FileUtils.copyFile(upload,file);
			//把客户新图片的路径更新到数据库中
			customer.setFilepath(path+uuidName);
		}
		//更新客户的信息
		customerService.update(customer);

		return "update";
	}

}












