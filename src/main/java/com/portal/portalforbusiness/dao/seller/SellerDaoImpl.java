package com.portal.portalforbusiness.dao.seller;

import com.portal.portalforbusiness.models.Seller;
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

public class SellerDaoImpl implements SellerDao {
    public void save(Seller seller) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.persist(seller);
            tx1.commit();
        }
    }

    public Optional<Seller> findByUsernameAndPassword(String username, String password) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Seller> cr = cb.createQuery(Seller.class);
            Root<Seller> root = cr.from(Seller.class);

            Predicate equalUsername = cb.equal(root.get("username"), username);
            Predicate equalPassword = cb.equal(root.get("password"), password);
            cr.select(root).where(cb.and(equalPassword, equalUsername));

            Query<Seller> query = session.createQuery(cr);
            Seller seller = query.getSingleResultOrNull();
            return Optional.ofNullable(seller);
        }
    }

    public Optional<List<Seller>> getAllSellers() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Seller> cr = cb.createQuery(Seller.class);

            Root<Seller> root = cr.from(Seller.class);
            cr.select(root);

            Query<Seller> query = session.createQuery(cr);
            List<Seller> sellers = query.getResultList();

            return Optional.of(sellers);
        }
    }
}
