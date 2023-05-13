package com.portal.portalforbusiness.dao.user;

import com.portal.portalforbusiness.models.Card;
import com.portal.portalforbusiness.models.RoleType;
import com.portal.portalforbusiness.models.User;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import com.portal.portalforbusiness.utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao{
    public Optional<User> findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);

            root.join("card", JoinType.LEFT);
            cr.select(root).where(cb.equal(root.get("id"), id));

            Query<User> query = session.createQuery(cr);
            return Optional.ofNullable(query.getSingleResult());
        }
    }
    public Optional<User> findByUsernameAndPassword(String username, String password) {
      try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

          HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
          CriteriaQuery<User> cr = cb.createQuery(User.class);
          Root<User> root = cr.from(User.class);

          Predicate equalUsername = cb.equal(root.get("username"), username);
          Predicate equalPassword = cb.equal(root.get("password"), password);
          cr.select(root).where(cb.and(equalPassword, equalUsername));

          Query<User> query = session.createQuery(cr);
          User user = query.getSingleResultOrNull();
          return Optional.ofNullable(user);
      }
    }

    public Optional<List<User>> getAllUsers(Integer limit) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root).orderBy(cb.asc(root.get("id")));

            Query<User> query = session.createQuery(cr);
            return Optional.ofNullable(query.getResultList());
        }
    }

    public void save(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.persist(user);
            tx1.commit();
        }
    }

    public Optional<User> update(User user, User newUser) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            User userToChange = session.get(User.class, user.getId());

            userToChange.setImagePath(newUser.getImagePath());
            userToChange.setUsername(newUser.getUsername());
            userToChange.setPassword(newUser.getPassword());

            session.merge(userToChange);
            tx.commit();
            return Optional.of(userToChange);
        }
    }

    public Optional<User> setCard(Integer userId, Card card , RoleType roleType) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            User userToChange = session.get(User.class, userId);

            userToChange.setRole(roleType);
            userToChange.setCard(card);

            session.merge(userToChange);
            tx.commit();
            return Optional.of(userToChange);
        }
    }

    public Optional<User> delete(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class, id);
            session.remove(user);
            tx.commit();
            return Optional.ofNullable(user);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<User> block(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class, id);
            user.setActive(!user.getActive());
            session.merge(user);

            tx.commit();
            return Optional.of(user);
        }
    }
}
