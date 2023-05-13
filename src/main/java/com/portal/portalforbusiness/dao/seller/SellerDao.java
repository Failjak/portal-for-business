package com.portal.portalforbusiness.dao.seller;


import com.portal.portalforbusiness.models.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerDao {

    Optional<Seller> findByUsernameAndPassword(String username, String password);

    void save(Seller seller);

    Optional<List<Seller>> getAllSellers();
}
