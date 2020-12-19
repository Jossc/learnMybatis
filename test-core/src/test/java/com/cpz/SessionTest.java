package com.cpz;

import com.cpz.exception.ResourceException;
import com.cpz.io.LoadDataConfigResource;
import com.cpz.io.Resources;
import com.cpz.sql.DefaultSqlSessionFactory;
import com.cpz.sql.SqlSessionFactory;
import com.cpz.sql.session.SqlSession;
import com.cpz.sql.session.SqlSessionFactoryBuilder;
import com.cpz.test.model.User;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName com.cpz.SessionTest.java
 * @createTime 2020年12月19日 23:21:00
 */

public class SessionTest {

    @Test
    public void test() throws Exception {
        LoadDataConfigResource loadDataConfigResource = new LoadDataConfigResource();
        InputStream inputStream =
                loadDataConfigResource.getResourceInputStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory
                = new SqlSessionFactoryBuilder().buildSqlSessionFactory(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> userList = sqlSession.selectList("com.cpz.test.dao.IUserDao.findAll", null);
        userList.forEach(System.out::println);
    }
}
