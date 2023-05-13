package com.portal.portalforbusiness.mapper.seller;


import com.portal.portalforbusiness.dto.SellerDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Seller;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerDtoMapper implements Mapper<SellerDto, Seller> {
    private static final SellerDtoMapper INSTANCE = new SellerDtoMapper();

    @Override
    public Seller mapFrom(SellerDto object) {
        Seller seller = new Seller();
        seller.setId(object.getId());
        seller.setPassword(object.getPassword());
        seller.setUsername(object.getUsername());
//        TODO smth here
//        seller.setProducts(object.getProducts().stream().map(product -> productDtoMapper.mapFrom(product)).collect(Collectors.toList()));
        seller.setStoreName(object.getStoreName());

        return seller;
    }

    public static SellerDtoMapper getInstance() {
        return INSTANCE;
    }
}
