package com.portal.portalforbusiness.mapper.user;

import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder().
                id(object.getId()).
                username(object.getUsername()).
                password(object.getPassword()).
                imagePath(object.getImagePath()).
                role(object.getRole()).
                card(object.getCard()).
                isActive(object.getActive()).
                created_at(object.getCreated_at()).
                updated_at(object.getUpdated_at()).
                build();
    }

    public UserDto mapFrom(User object, List<Integer> boughtProductIds) {
        return UserDto.builder().
                id(object.getId()).
                username(object.getUsername()).
                password(object.getPassword()).
                imagePath(object.getImagePath()).
                role(object.getRole()).
                card(object.getCard()).
                boughtProductIds(boughtProductIds).
                isActive(object.getActive()).
                created_at(object.getCreated_at()).
                updated_at(object.getUpdated_at()).
                build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
