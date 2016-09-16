package com.hibernate.test;

import com.hibernate.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/8/3.
 */
public class TeacherTest {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    @BeforeClass
    public static void createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void testTeacherSave(){
        Teacher t = new Teacher();
        t.setName("a");
        t.setLevel(1);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

}