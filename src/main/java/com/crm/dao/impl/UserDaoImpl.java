package com.crm.dao.impl;

import com.crm.dao.UserDao;
import com.crm.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/8/9.
 */
/**
 * 持久层：都可以继承HibernateDaoSupport类
 * */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    /**
     * 通过登录名进行校验
     * */
    public User checkCode(String user_code) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?", user_code);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存用户
     * */
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    /**
     * 登录功能
     * 通过用户名和密码和用户的状态
     * */
    public User login(User user) {
        //QBC的查询，按条件进行查询
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        //拼接查询的条件
        criteria.add(Restrictions.eq("user_code",user.getUser_code()));
        criteria.add(Restrictions.eq("user_password",user.getUser_password()));
        criteria.add(Restrictions.eq("user_state","1"));

        //查询
        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
        if(list !=null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
