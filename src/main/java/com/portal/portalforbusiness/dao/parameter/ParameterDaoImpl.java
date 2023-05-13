package com.portal.portalforbusiness.dao.parameter;

import com.portal.portalforbusiness.models.Parameter;
import com.portal.portalforbusiness.models.ProductType;
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

public class ParameterDaoImpl {
    public Optional<List<Parameter>> getParametersByProductType(ProductType productType) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Parameter> cr = cb.createQuery(Parameter.class);
            Root<Parameter> root = cr.from(Parameter.class);

            Predicate equalProductType = cb.equal(root.get("productType"), productType);
            Predicate isActive = cb.equal(root.get("isActive"), Boolean.TRUE);
            cr.select(root).where(equalProductType, isActive);

            Query<Parameter> query = session.createQuery(cr);
            List<Parameter> parameters = query.getResultList();

            return Optional.of(parameters);
        }
    }

    public Optional<List<Parameter>> getAllParameters() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Parameter> cr = cb.createQuery(Parameter.class);

            Root<Parameter> root = cr.from(Parameter.class);
            cr.select(root).orderBy(cb.asc(root.get("productType")), cb.asc(root.get("id")));

            Query<Parameter> query = session.createQuery(cr);
            return Optional.ofNullable(query.getResultList());
        }
    }

    public void save(Parameter parameter) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.persist(parameter);
            tx1.commit();
        }
    }

    public Optional<Parameter> delete(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Parameter parameter = session.get(Parameter.class, id);
            session.remove(parameter);
            tx.commit();
            return Optional.ofNullable(parameter);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Parameter> block(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Parameter parameter = session.get(Parameter.class, id);
            parameter.setActive(!parameter.getActive());
            session.merge(parameter);

            tx.commit();
            return Optional.of(parameter);
        }
    }
}
