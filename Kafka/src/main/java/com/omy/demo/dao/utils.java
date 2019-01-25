package com.omy.demo.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: lijiayang
 * @date: 2019-01-22 16:06
 * @description:
 */

public class utils {
    private static SqlSessionFactory sqlSessionFactory;
    static{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            InputStream is = Resources.getResourceAsStream("mybaties.xml");
            sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }

    public static void closeSession(SqlSession sqlSession){
        if(sqlSession != null){
            sqlSession.close();
        }
    }
}
