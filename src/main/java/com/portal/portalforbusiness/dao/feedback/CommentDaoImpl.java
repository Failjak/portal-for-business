package com.portal.portalforbusiness.dao.feedback;

import com.portal.portalforbusiness.models.Comment;
import com.portal.portalforbusiness.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;


import java.util.List;
import java.util.Optional;

public class CommentDaoImpl {
    public Optional<Comment> save(Comment comment) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(comment);
            tx.commit();
            return Optional.of(comment);
        }
    }

    public Optional<List<Comment>> getAllCommentsByProductId(Integer productId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Comment> cr = cb.createQuery(Comment.class);
            Root<Comment> root = cr.from(Comment.class);

            Predicate equalProductId = cb.equal(root.get("productId"), productId);
            cr.select(root).where(equalProductId);

            Query<Comment> query = session.createQuery(cr);
            List<Comment> comments = query.getResultList();

            return Optional.of(comments);
        }
    }
}
