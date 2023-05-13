package com.portal.portalforbusiness.services;


import com.portal.portalforbusiness.dao.parameter.ParameterDaoImpl;
import com.portal.portalforbusiness.dto.ParameterDto;
import com.portal.portalforbusiness.dto.ProductTypeParametersDto;
import com.portal.portalforbusiness.mapper.parameter.ParameterDtoMapper;
import com.portal.portalforbusiness.mapper.parameter.ParameterMapper;
import com.portal.portalforbusiness.models.Parameter;
import com.portal.portalforbusiness.models.ProductType;

import java.util.List;
import java.util.Optional;

public class ParameterService implements Service {
    private static final ParameterService INSTANCE = new ParameterService();

    private final ParameterDaoImpl parameterDao = new ParameterDaoImpl();
    private final ParameterMapper parameterMapper = ParameterMapper.getInstance();
    private final ParameterDtoMapper parameterDtoMapper = ParameterDtoMapper.getInstance();

    public Optional<ProductTypeParametersDto> getParametersByProductType(ProductType productType) {
        Optional<List<Parameter>> parametersOptional = parameterDao.getParametersByProductType(productType);

        return parametersOptional.map(parameters -> ProductTypeParametersDto.builder().
                productType(productType).
                parameters(parameters.stream().map(parameterMapper::mapFrom).toList()).
                build());
    }

    public Optional<List<ParameterDto>> getAllParameters() {
        Optional<List<Parameter>> parametersOptional = parameterDao.getAllParameters();
        if (parametersOptional.isEmpty())
            return Optional.empty();
        return Optional.of(parametersOptional.get().stream().map(parameterMapper::mapFrom).toList());
    }

    public void save(ParameterDto parameterDto) {
        parameterDao.save(parameterDtoMapper.mapFrom(parameterDto));
    }

    public Optional<ParameterDto> block(ParameterDto parameterDto) {
        return parameterDao.block(parameterDto.getId()).
                map(parameterMapper::mapFrom);
    }

    public Optional<ParameterDto> delete(ParameterDto parameterDto) {
        return parameterDao.delete(parameterDto.getId()).
                map(parameterMapper::mapFrom);
    }

    public static ParameterService getInstance() {
        return INSTANCE;
    }
}
