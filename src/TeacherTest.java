import com.hibernate.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Administrator on 2016/8/2.
 */
public class TeacherTest {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static void main(String args[]){
        Teacher t = new Teacher();
        t.setName("a");
        t.setLevel(1);

        sessionFactory = TeacherTest.createSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
