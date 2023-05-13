package com.portal.portalforbusiness.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserProductDto {
    Integer id;
    Integer userId;
    Integer productId;
}
