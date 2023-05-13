package com.portal.portalforbusiness.dto.responses;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class FeedbackDto {
    Integer productId;
    Map<Integer, Integer> evaluation;
    String comment;
}
