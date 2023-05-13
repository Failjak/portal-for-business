package com.portal.portalforbusiness.dto;

import com.portal.portalforbusiness.models.ProductType;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProductTypeParametersDto {
    ProductType productType;
    List<ParameterDto> parameters;
}
