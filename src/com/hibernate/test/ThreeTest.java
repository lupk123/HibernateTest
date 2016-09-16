package com.hibernate.test;

import com.hibernate.model.Course;
import com.hibernate.model.Score;
import com.hibernate.model.Student;
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
public class ThreeTest {
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
    public void testThreeSave(){
        Student s = new Student();
        s.setName("张三");
        Course c = new Course();
        c.setName("数学");
        Score score = new Score();
        score.setScore(89);
        score.setCourse(c);
        score.setStudent(s);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(s);
        session.save(c);
        session.save(score);
        session.getTransaction().commit();
        session.close();
//        sessionFactory.close();
    }

    @Test
    public void testLoad(){
        testThreeSave();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Score s = (Score)session.load(Score.class, 1);
        System.out.println(s.getScore());
        System.out.println(s.getStudent().getName());
        System.out.println(s.getCourse().getName());
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

}