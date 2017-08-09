package com.crm.web.action;

import com.crm.domain.User;
import com.crm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Taeyeon-Serenity on 2017/8/9.
 */
/**
 * 用户的控制器
 * */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user=new User();

    public User getModel() {
        return user;
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册功能
     * */
    public String regist(){
        //接收请求参数
        userService.save(user);
        return LOGIN;
    }

    /**
     * 通过登录名，判断登录名是否已经存在
     * */
    public String checkCode(){
        //调用业务层去查询
        User u=userService.checkCode(user.getUser_code());
        //获取response对象
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        //获取输出流
        try {
            PrintWriter writer = response.getWriter();
            //进行判断
            if(u != null){
                //说明：登录名查询到用户
                writer.print("no");
            }else {
                writer.print("yes");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }

    /**
     * 登录功能
     * */
    public String login(){
        User existUser=userService.login(user);
        //判断 登录名或密码错误或者状态为0
        if(existUser == null){
            return LOGIN;
        }else {
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return "loginOK";
        }
    }
}
