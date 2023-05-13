package com.portal.portalforbusiness.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
