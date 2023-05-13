package com.portal.portalforbusiness.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CardDto {
    Integer id;
    String ccn;
    String cvv;
    String year;
    String month;
    Float balance;
}
