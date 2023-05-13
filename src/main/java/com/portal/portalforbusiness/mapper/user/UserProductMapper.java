package com.portal.portalforbusiness.mapper.user;

import com.portal.portalforbusiness.dto.UserProductDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.UserProduct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProductMapper implements Mapper<UserProduct, UserProductDto> {
    private static final UserProductMapper INSTANCE = new UserProductMapper();

    @Override
    public UserProductDto mapFrom(UserProduct object) {
        return UserProductDto.builder().
                id(object.getId()).
                userId(object.getUserId()).
                productId(object.getProductId()).
                build();
    }

    public static UserProductMapper getInstance() {
        return INSTANCE;
    }
}