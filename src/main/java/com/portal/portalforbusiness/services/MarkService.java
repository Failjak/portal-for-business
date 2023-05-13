package com.portal.portalforbusiness.services;

import com.portal.portalforbusiness.dao.feedback.MarkDaoImpl;
import com.portal.portalforbusiness.dto.MarkDto;
import com.portal.portalforbusiness.mapper.feedback.MarkDtoMapper;
import com.portal.portalforbusiness.mapper.feedback.MarkMapper;
import com.portal.portalforbusiness.models.Comment;


import java.util.List;

public class MarkService implements Service {
    private static final MarkService INSTANCE = new MarkService();
    private final MarkMapper markMapper = MarkMapper.getInstance();
    private final MarkDtoMapper markDtoMapper = MarkDtoMapper.getInstance();
    private final MarkDaoImpl markDao = new MarkDaoImpl();

    public void save(List<MarkDto> marksDto, Comment comment) {
        markDao.save(marksDto.stream().map(mark -> markDtoMapper.mapFrom(mark, comment)).toList());
    }

    public static MarkService getInstance() {
        return INSTANCE;
    }
}
