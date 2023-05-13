package com.portal.portalforbusiness.mapper.parameter;


import com.portal.portalforbusiness.dto.ParameterDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Parameter;

public class ParameterMapper implements Mapper<Parameter, ParameterDto> {
    private static final ParameterMapper INSTANCE = new ParameterMapper();

    public ParameterDto mapFrom(Parameter object) {
        return ParameterDto.builder().
                id(object.getId()).
                name(object.getName()).
                isActive(object.getActive()).
                productType(object.getProductType()).
                build();
    }

    public static ParameterMapper getInstance() {
        return INSTANCE;
    }
}
