package com.portal.portalforbusiness.mapper.seller;

import com.portal.portalforbusiness.dto.SellerDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.mapper.product.ProductMapper;
import com.portal.portalforbusiness.models.Seller;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerMapper implements Mapper<Seller, SellerDto> {
    private static final SellerMapper INSTANCE = new SellerMapper();

    private final ProductMapper productMapper = ProductMapper.getInstance();

    @Override
    public SellerDto mapFrom(Seller object) {
        return SellerDto.builder().
                id(object.getId()).
                username(object.getUsername()).
                password(object.getPassword()).
                storeName(object.getStoreName()).
                products(object.getProducts().stream().map(productMapper::mapFrom).collect(Collectors.toList())).
                build();
    }

    public SellerDto mapWithoutProducts(Seller object) {
        return SellerDto.builder().
                id(object.getId()).
                username(object.getUsername()).
                password(object.getPassword()).
                storeName(object.getStoreName()).
                build();
    }

    public static SellerMapper getInstance() {
        return INSTANCE;
    }
}
