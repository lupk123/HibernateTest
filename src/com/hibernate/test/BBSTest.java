package com.hibernate.test;

import com.hibernate.model.Category;
import com.hibernate.model.Msg;
import com.hibernate.model.Topic;
import com.hibernate.model.Tree;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/3.
 */
public class BBSTest {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    /**
     * 自动创建表格 并且生成sessionFactory
     */
    @BeforeClass
    public static void createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        // 创建 SchemeExport 实例
        SchemaExport sExport = new SchemaExport(configuration);
        // 创建数据库表
        /*
         * 创建表结构
         * 第一个参数 script 的作用：print the DDL to the console
         * 第二个参数 export 的作用：export the script to the database
         */
        sExport.create(true, true);
        serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @AfterClass
    public static void closeSessionFactory(){
        sessionFactory.close();
    }

    @Test
    public void testTreeSave(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i = 0; i < 10; i++){
            Category c = new Category();
            c.setName("c" + i);
            session.save(c);
        }

        for(int i = 0; i < 10; i++){
            Category c = new Category();
            c.setId(1);
            Topic t = new Topic();
            t.setTitle("t" + i);
            t.setCreateDate(new Date());
            t.setCategory(c);
            session.save(t);
        }

        for(int i = 0; i < 10; i++){
            Topic t = new Topic();
            t.setId(1);
            Msg m = new Msg();
            m.setCont("m" + i);
            m.setTopic(t);
            session.save(m);
        }

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testLoad(){
//        testTreeSave();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Tree t = (Tree)session.load(Tree.class, 1);
//        print(t);
//        session.getTransaction().commit();
//        session.close();
//        sessionFactory.close();
    }

}