package com.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Administrator on 2016/8/1.
 */
public class main {
    private static SessionFactory sessionFactory;
    private  static ServiceRegistry serviceRegistry;

    public static void main(String args[]){
        User u = new User();
        u.setName("a");
        u.setDes("b");

        main.createSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(u);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    private static void createSessionFactory(){
        Configuration cfg = new Configuration();
        cfg.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(
               cfg.getProperties()). buildServiceRegistry();
        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }
}
