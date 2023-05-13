package com.portal.portalforbusiness.mapper.product;

import com.portal.portalforbusiness.dto.ProductDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper implements Mapper<Product, ProductDto> {
    private static final ProductMapper INSTANCE = new ProductMapper();

    @Override
    public ProductDto mapFrom(Product object) {
        return ProductDto.builder().
                id(object.getId()).
                type(object.getType()).
                imagePath(object.getImagePath()).
                description(object.getDescription()).
                name(object.getName()).
                price(object.getPrice()).
                currency(object.getCurrencyType()).
                build();
    }

    public ProductDto mapFrom(Product object, String json, Double avgMark) {
        return ProductDto.builder().
                id(object.getId()).
                type(object.getType()).
                imagePath(object.getImagePath()).
                description(object.getDescription()).
                name(object.getName()).
                avgMark(avgMark).
                json(json).
                price(object.getPrice()).
                currency(object.getCurrencyType()).
                build();
    }

    public static ProductMapper getInstance() {
        return INSTANCE;
    }
}
