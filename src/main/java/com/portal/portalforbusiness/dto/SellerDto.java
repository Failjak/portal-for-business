package com.portal.portalforbusiness.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class SellerDto {
    Integer id;
    String username;
    String password;
    String storeName;
    List<ProductDto> products;
}
