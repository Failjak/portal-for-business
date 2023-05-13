package com.portal.portalforbusiness.dao.product;

import com.portal.portalforbusiness.models.Product;
import com.portal.portalforbusiness.models.Seller;
import com.portal.portalforbusiness.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    public void save(Product product) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.persist(product);
            tx1.commit();
        }
    }

    public Optional<List<Product>> findAllProductsByName(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);

            Predicate equalName = cb.ilike(root.get("name"), name);
            cr.select(root).where(equalName);

            Query<Product> query = session.createQuery(cr);
            List<Product> products = query.getResultList();
            return Optional.ofNullable(products);
        }
    }

    public Optional<List<Product>> findProductsBySeller(Seller seller) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);

            Predicate equalSeller = cb.equal(root.get("seller"), seller);
            cr.select(root).where(equalSeller);

            Query<Product> query = session.createQuery(cr);
            List<Product> products = query.getResultList();
            return Optional.ofNullable(products);
        }
    }

    public Optional<Product> delete(Integer id, Seller seller) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);

            Predicate equalSeller = cb.equal(root.get("seller"), seller);
            Predicate equalId = cb.equal(root.get("id"), id);
            cr.select(root).where(cb.and(equalId, equalSeller));

            Query<Product> query = session.createQuery(cr);
            Product product = query.getSingleResultOrNull();

            session.remove(product);
            tx.commit();
            return Optional.ofNullable(product);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<List<Product>> getAllProducts() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);

            Root<Product> root = cr.from(Product.class);
            cr.select(root);

            Query<Product> query = session.createQuery(cr);
            return Optional.ofNullable(query.getResultList());
        }
    }

    public Map<Integer, Double> getProductsAvgMark() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String sql = "select p.id, avg(m.evaluate) as avgMark from Product as p join Comment as c on p.id = c.productId join Mark as m on c.id = m.comment group by p.id";
            List<Object[]> resultList = session.createQuery(sql, Object[].class).getResultList();

            Map<Integer, Double> productsWithAvgMark = new HashMap<>();
            for (Object[] product : resultList) {
                productsWithAvgMark.put((Integer) product[0], (Double) product[1]);
            }

            return productsWithAvgMark;
        }
    }

    public Optional<Product> findProductById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.of(session.get(Product.class, id));
        }
    }
}
