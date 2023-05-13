package com.portal.portalforbusiness.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class CommentDto {
    Integer id;
    String comment;
    UserDto user;
    Integer productId;
    List<MarkDto> marks;
    @JsonIgnore
    LocalDateTime created_at;
}
