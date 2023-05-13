package com.portal.portalforbusiness.dto;

import com.portal.portalforbusiness.models.Comment;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MarkDto {
    Integer id;
    Comment comment;
    Integer parameterId;
    Integer evaluate;
}
