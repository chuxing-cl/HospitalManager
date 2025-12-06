package com.czy.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 陈政缘
 * Date  2025/7/1 15:06
 */
public class SqlSessionUtil {
    //创建私有构造方法
    private SqlSessionUtil() {
    }

    private static SqlSessionFactory factory;

    //使用静态代码块,读取配置文件 ,并获取SqlSessionFactory对象
    static {
        try (
                InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        ) {
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义ThreadLocal 存储每个线程SqlSession对象
    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /**
     * 获取SqlSession对象的方法
     *
     * @return
     */
    public static SqlSession getSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = factory.openSession();
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭SqlSession的方法
     */
    public static void closeSession() {
        try {
            SqlSession sqlSession = local.get();
            if (sqlSession != null) {
                sqlSession.close();
            }
        } finally {
            local.remove();
        }
    }

    /**
     * 事务提交的方法
     */
    public static void commit() {
        SqlSession sqlSession = local.get();
        sqlSession.commit();
    }

    /**
     * 事务回滚的方法
     */
    public static void rollback() {
        SqlSession sqlSession = local.get();
        sqlSession.rollback();
    }

    /**
     * 获取指定类型的Mapper对象
     *
     * @param type
     * @param <T>
     * @return Mapper对象
     */
    public static <T> T getMapper(Class<T> type) {
        return getSession().getMapper(type);
    }
}
