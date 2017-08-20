package com.crm.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crm.domain.Customer;
import com.crm.domain.Person;
import com.crm.domain.Role;
import org.apache.struts2.interceptor.DateTextFieldInterceptor;
import org.junit.Test;

import java.awt.image.RasterOp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.apache.struts2.interceptor.DateTextFieldInterceptor.DateWord.s;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */
public class Demo1 {

    /**
     * 演示fastjson的简单的使用
     * */
    @Test
    public void run1(){
        Customer c=new Customer();
        c.setCust_id(20L);
        c.setCust_name("测试");
        c.setCust_phone("120");

        //转换成json的字符串
        String jsonString = JSON.toJSONString(c);
        System.out.println(jsonString);
    }

    @Test
    public void run2(){
        List<Customer> list=new ArrayList<Customer>();
        Customer c=new Customer();
        c.setCust_id(20L);
        c.setCust_name("测试");
        c.setCust_phone("120");
        list.add(c);

        Customer c2=new Customer();
        c2.setCust_id(30L);
        c2.setCust_name("测试2");
        c2.setCust_phone("12000");
        list.add(c2);

        //转换成json的字符串
        String s = JSON.toJSONString(list);
        System.out.println(s);
    }

    @Test
    public void run3(){
        List<Customer> list=new ArrayList<Customer>();
        Customer c=new Customer();
        c.setCust_id(20L);
        c.setCust_name("测试");
        c.setCust_phone("120");
        list.add(c);
        list.add(c);

        //json传入两个相同的对象，后面的对象只是引用，浏览器中无法解析
//        String s = JSON.toJSONString(list);

        //禁止循环的引用
        String s = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(s);
    }

    /**
     * 死循环的问题
     * */
    @Test
    public void run4(){
        Person p=new Person();
        p.setPname("美美");

        Role r=new Role();
        r.setRname("管理员");

        p.setRole(r);
        r.setPerson(p);

        //禁止循环的引用
        String s = JSON.toJSONString(r, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(s);
    }
}
