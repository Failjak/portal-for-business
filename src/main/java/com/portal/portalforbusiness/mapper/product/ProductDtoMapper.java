package com.portal.portalforbusiness.mapper.product;

import com.portal.portalforbusiness.dto.ProductDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDtoMapper implements Mapper<ProductDto, Product> {
    private static final ProductDtoMapper INSTANCE = new ProductDtoMapper();

    @Override
    public Product mapFrom(ProductDto object) {
        Product product = new Product();
        product.setId(object.getId());
        product.setDescription(object.getDescription());
        product.setType(object.getType());
        product.setImagePath(object.getImagePath());
        product.setName(object.getName());
        product.setCurrency(object.getCurrency());
        product.setPrice(object.getPrice());
//        smth heere
//        product.setSeller(sellerDtoMapper.mapFrom(object.getSeller()));

        return product;
    }

    public static ProductDtoMapper getInstance() {
        return INSTANCE;
    }
}
