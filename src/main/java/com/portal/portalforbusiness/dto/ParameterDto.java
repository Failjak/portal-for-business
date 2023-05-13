package com.portal.portalforbusiness.dto;

import com.portal.portalforbusiness.models.ProductType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParameterDto {
    Integer id;
    ProductType productType;
    String name;
    Boolean isActive;
}
