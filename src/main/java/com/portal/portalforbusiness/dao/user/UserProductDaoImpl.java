package com.portal.portalforbusiness.dao.user;

import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.models.Card;
import com.portal.portalforbusiness.models.Product;
import com.portal.portalforbusiness.models.UserProduct;
import com.portal.portalforbusiness.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserProductDaoImpl {
    public Optional<UserProduct> save(UserProduct userProduct, UserDto userDto) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            session.persist(userProduct);

            Card cardWithChangedBalance = session.get(Card.class, userDto.getCard().getId());
            cardWithChangedBalance.setBalance(userDto.getCard().getBalance());
            session.merge(cardWithChangedBalance);

            tx.commit();
            return Optional.of(userProduct);
        }
    }

    public Optional<List<Integer>> getBoughtProductIdsByUser(Integer userId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            String sql = "select productId from UserProduct where userId=" + userId;
            List<Integer> boughtProductIds = session.createQuery(sql, Integer.class).getResultList();

            return Optional.of(boughtProductIds);
        }
    }

    public Optional<List<Product>> getBoughtProductsByUser(Integer userId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            String sqlGetIds = "select productId from UserProduct where userId=" + userId;
            List<Integer> boughtProductIds = session.createQuery(sqlGetIds, Integer.class).getResultList();
            String boughtProductIdsString = boughtProductIds.toString().replace("[", "(").replace("]", ")");

            String sqlGetProducts = "from Product where id  IN " + boughtProductIdsString;
            List<Object> boughtProducts = session.createQuery(sqlGetProducts, Object.class).getResultList();

            List<Product> products = new ArrayList<>();
            for (Object boughtProduct : boughtProducts){
                products.add((Product) boughtProduct);
            }

            return Optional.of(products);
        }
    }
}
