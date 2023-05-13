package com.portal.portalforbusiness.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.portalforbusiness.models.Product;
import com.portal.portalforbusiness.models.ProductType;
import lombok.Builder;
import lombok.Value;

import java.util.Currency;

@Value
@Builder
public class ProductDto {
    Integer id;
    String name;
    String imagePath;
    Float price;
    Double avgMark;
    Currency currency;
    String description;
    ProductType type;
    SellerDto seller;
    String json;

    public static String getJson (Product product) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            product.setSeller(null);
            return mapper.writeValueAsString(product);
        } catch (JsonProcessingException | StackOverflowError e) {
            throw new RuntimeException(e);
        }
    }
}
