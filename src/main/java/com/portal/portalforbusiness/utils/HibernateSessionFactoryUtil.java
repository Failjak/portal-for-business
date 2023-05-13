package com.portal.portalforbusiness.utils;
import com.portal.portalforbusiness.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Seller.class);
                configuration.addAnnotatedClass(Parameter.class);
                configuration.addAnnotatedClass(Comment.class);
                configuration.addAnnotatedClass(Mark.class);
                configuration.addAnnotatedClass(Card.class);
                configuration.addAnnotatedClass(UserProduct.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("HibernateSessionFactoryUtil error!" + e);
            }
        }
        return sessionFactory;
    }
}
