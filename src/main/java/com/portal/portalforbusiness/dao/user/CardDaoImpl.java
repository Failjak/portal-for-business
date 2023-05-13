package com.portal.portalforbusiness.dao.user;

import com.portal.portalforbusiness.models.Card;
import com.portal.portalforbusiness.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class CardDaoImpl {
    public Optional<Card> save(Card card) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(card);
            tx.commit();

            return Optional.of(card);
        }
    }

    public Optional<Card> delete(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Card card = session.get(Card.class, id);
            session.remove(card);
            tx.commit();
            return Optional.ofNullable(card);

        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
