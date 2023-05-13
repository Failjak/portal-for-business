package com.portal.portalforbusiness.mapper.user;

import com.portal.portalforbusiness.dto.UserProductDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.UserProduct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProductDtoMapper implements Mapper<UserProductDto, UserProduct> {
    private static final UserProductDtoMapper INSTANCE = new UserProductDtoMapper();

    @Override
    public UserProduct mapFrom(UserProductDto object) {
        UserProduct userProduct = new UserProduct();
        userProduct.setId(object.getId());
        userProduct.setUserId(object.getUserId());
        userProduct.setProductId(object.getProductId());
        return userProduct;
    }

    public static UserProductDtoMapper getInstance() {
        return INSTANCE;
    }
}
