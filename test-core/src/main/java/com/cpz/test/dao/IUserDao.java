package com.cpz.test.dao;

import com.cpz.test.model.User;

import java.util.List;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName IUserDao.java
 * @createTime 2020年12月19日 23:15:00
 */
public interface IUserDao {

    List<User> findAll();

    User findByCondition(User user);
}
