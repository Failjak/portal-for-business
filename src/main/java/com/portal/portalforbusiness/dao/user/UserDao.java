package com.portal.portalforbusiness.dao.user;


import com.portal.portalforbusiness.models.Card;
import com.portal.portalforbusiness.models.RoleType;
import com.portal.portalforbusiness.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Integer id);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<List<User>> getAllUsers(Integer limit);

    void save(User user);

    Optional<User> update(User user, User newUser);

    Optional<User> setCard(Integer userId, Card card, RoleType roleType);

    Optional<User> delete(Integer id);
    Optional<User> block(Integer id);
}
