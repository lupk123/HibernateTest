package com.hibernate.test;

import com.hibernate.model.Teacher;
import com.hibernate.model.Tree;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Administrator on 2016/8/3.
 */
public class TreeTest {
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

    @Test
    public void testTreeSave(){
        Tree t = new Tree();
        t.setName("总公司");
        Tree t1 = new Tree();
        t1.setName("分公司1");
        Tree t11 = new Tree();
        t11.setName("分公司1下部门1");
        Tree t2 = new Tree();
        t2.setName("分公司2");
        Tree t21 = new Tree();
        t21.setName("分公司2下部门1");

        t.getChilds().add(t1);
        t.getChilds().add(t2);
        t1.getChilds().add(t11);
        t2.getChilds().add(t21);

        t11.setParent(t1);
        t21.setParent(t2);
        t1.setParent(t);
        t2.setParent(t);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.clear();
        session.save(t);
        session.getTransaction().commit();
        session.close();
//        sessionFactory.close();
    }

    @Test
    public void testLoad(){
        testTreeSave();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tree t = (Tree)session.load(Tree.class, 1);
        print(t);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private void print(Tree t) {
        System.out.println(t.getName());

        t.getChilds().forEach(
                item -> print(item)
        );
    }

}