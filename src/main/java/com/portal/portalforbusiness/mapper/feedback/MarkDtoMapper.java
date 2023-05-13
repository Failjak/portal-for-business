package com.portal.portalforbusiness.mapper.feedback;

import com.portal.portalforbusiness.dto.CardDto;
import com.portal.portalforbusiness.dto.MarkDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Comment;
import com.portal.portalforbusiness.models.Mark;


public class MarkDtoMapper implements Mapper<MarkDto, Mark> {
    private static final MarkDtoMapper INSTANCE = new MarkDtoMapper();

    @Override
    public Mark mapFrom(MarkDto object) {
        Mark mark = new Mark();
        mark.setId(object.getId());
        mark.setEvaluate(object.getEvaluate());
        mark.setParameterId(object.getParameterId());
        return mark;
    }

    public Mark mapFrom(MarkDto object, Comment comment) {
        Mark mark = new Mark();
        mark.setId(object.getId());
        mark.setComment(comment);
        mark.setEvaluate(object.getEvaluate());
        mark.setParameterId(object.getParameterId());
        return mark;
    }

    public static MarkDtoMapper getInstance() {return INSTANCE;}
}
