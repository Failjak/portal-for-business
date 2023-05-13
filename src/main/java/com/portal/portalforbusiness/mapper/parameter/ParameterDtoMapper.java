package com.portal.portalforbusiness.mapper.parameter;

import com.portal.portalforbusiness.dto.ParameterDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Parameter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParameterDtoMapper implements Mapper<ParameterDto, Parameter> {
    private static final ParameterDtoMapper INSTANCE = new ParameterDtoMapper();

    @Override
    public Parameter mapFrom(ParameterDto object) {
        Parameter parameter = new Parameter();

        parameter.setId(object.getId());
        parameter.setName(object.getName());
        parameter.setActive(object.getIsActive());
        parameter.setProductType(object.getProductType());

        return parameter;
    }

    public static ParameterDtoMapper getInstance() {
        return INSTANCE;
    }
}
