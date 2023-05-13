package com.portal.portalforbusiness.mapper.user;

import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.portal.portalforbusiness.dto.UserDto;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoMapper implements Mapper<UserDto, User> {
    private static final UserDtoMapper INSTANCE = new UserDtoMapper();

    @Override
    public User mapFrom(UserDto object) {
        User user = new User();
        user.setId(object.getId());
        user.setPassword(object.getPassword());
        user.setUsername(object.getUsername());
        user.setImagePath(object.getImagePath());
        user.setRole(object.getRole());
        user.setCard(object.getCard());
        user.setActive(object.getIsActive());
        user.setCreated_at(object.getCreated_at());
        user.setUpdated_at(object.getUpdated_at());

        return user;
    }

    public static UserDtoMapper getInstance() {
        return INSTANCE;
    }
}
