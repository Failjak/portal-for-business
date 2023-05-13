package com.portal.portalforbusiness.services;

import com.portal.portalforbusiness.dao.product.ProductDao;
import com.portal.portalforbusiness.dao.product.ProductDaoImpl;
import com.portal.portalforbusiness.dao.seller.SellerDao;
import com.portal.portalforbusiness.dao.seller.SellerDaoImpl;
import com.portal.portalforbusiness.dto.SellerDto;
import com.portal.portalforbusiness.mapper.seller.SellerDtoMapper;
import com.portal.portalforbusiness.mapper.seller.SellerMapper;
import com.portal.portalforbusiness.models.Product;
import com.portal.portalforbusiness.models.Seller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SellerService implements Service{
    private static final SellerService INSTANCE = new SellerService();

    private final SellerDao sellerDao = new SellerDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();
    private final SellerMapper sellerMapper = SellerMapper.getInstance();
    private final SellerDtoMapper sellerDtoMapper = SellerDtoMapper.getInstance();
    private final ProductService productService = ProductService.getInstance();

    public void saveSeller(SellerDto sellerDto) {
        sellerDao.save(sellerDtoMapper.mapFrom(sellerDto));
    }


    public Optional<SellerDto> login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return Optional.empty();
        }
        Optional<Seller> sellerOptional = sellerDao.findByUsernameAndPassword(username, password);

        sellerOptional.ifPresent(seller -> {
            Optional<List<Product>> productsOptional = productService.getProductsBySeller(seller);
            productsOptional.ifPresent(seller::setProducts);

        });
        return sellerOptional.map(sellerMapper::mapFrom);
    }

    public List<SellerDto> getAllSellers() {
        Optional<List<Seller>> sellersOptional = sellerDao.getAllSellers();

        if (sellersOptional.isPresent()) {
            List<SellerDto> sellers = new ArrayList<>();
            for (Seller seller : sellersOptional.get()) {
                productDao.findProductsBySeller(seller).ifPresent(seller::setProducts);
                sellers.add(sellerMapper.mapFrom(seller));
            }
            return sellers;
        }
        return new ArrayList<>();
    }

    public Optional<List<SellerDto>> getAllSellersWithoutProducts() {
        return sellerDao.getAllSellers().map(sellers -> sellers.stream().map(sellerMapper::mapWithoutProducts).collect(Collectors.toList()));
    }

    public static SellerService getInstance() {
        return INSTANCE;
    }

}
