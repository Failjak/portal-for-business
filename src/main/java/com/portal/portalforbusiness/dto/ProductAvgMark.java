package com.portal.portalforbusiness.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductAvgMark {
    Integer id;
    Double avgMark;
}
