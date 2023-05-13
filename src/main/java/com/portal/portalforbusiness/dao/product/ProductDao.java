package com.portal.portalforbusiness.dao.product;


import com.portal.portalforbusiness.models.Product;
import com.portal.portalforbusiness.models.Seller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductDao {
    void save(Product product);
    Optional<List<Product>> findProductsBySeller(Seller seller);
    Optional<Product> delete(Integer id, Seller seller);
    Optional<List<Product>> getAllProducts();
    Optional<List<Product>> findAllProductsByName(String name);
    Optional<Product> findProductById(Integer id);
    Map<Integer, Double>  getProductsAvgMark();
}
