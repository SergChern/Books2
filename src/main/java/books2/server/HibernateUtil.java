package books2.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        /*
         * Turning off the hibernate logging - now it shows only warnings.
         */
        Logger logger = Logger.getLogger("org.hibernate");
        logger.setLevel(Level.WARNING);

        try {
            return new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        Session session = getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive())
            session.beginTransaction();

        return session;
    }    
}
