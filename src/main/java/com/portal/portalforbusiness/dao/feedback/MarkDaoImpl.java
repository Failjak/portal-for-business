package com.portal.portalforbusiness.dao.feedback;

import com.portal.portalforbusiness.models.Mark;
import com.portal.portalforbusiness.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MarkDaoImpl {
    public void save(List<Mark> marks) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            for (Mark mark : marks)
                session.persist(mark);
            tx.commit();
        }
    }
}
