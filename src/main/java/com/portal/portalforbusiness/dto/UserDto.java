package com.portal.portalforbusiness.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.portalforbusiness.models.Card;
import lombok.Builder;
import lombok.Value;
import com.portal.portalforbusiness.models.RoleType;


import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class UserDto {
    Integer id;
    String username;
    String password;
    RoleType role;
    String imagePath;
    Card card;
    @JsonIgnore
    LocalDateTime created_at;
    @JsonIgnore
    LocalDateTime updated_at;
    Boolean isActive;
    List<Integer> boughtProductIds;
}
