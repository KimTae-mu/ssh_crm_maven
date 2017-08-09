package com.crm.dao;

import com.crm.domain.User;

/**
 * Created by Taeyeon-Serenity on 2017/8/9.
 */
public interface UserDao {
    public User checkCode(String user_code);

    public void save(User user);

    public User login(User user);
}
