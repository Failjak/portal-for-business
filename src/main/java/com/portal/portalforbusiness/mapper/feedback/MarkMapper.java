package com.portal.portalforbusiness.mapper.feedback;


import com.portal.portalforbusiness.dto.MarkDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Mark;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MarkMapper implements Mapper<Mark, MarkDto> {
    private static final MarkMapper INSTANCE = new MarkMapper();

    @Override
    public MarkDto mapFrom(Mark object) {
        return MarkDto.builder().
                id(object.getId()).
                evaluate(object.getEvaluate()).
                build();
    }

    public static MarkMapper getInstance() {
        return INSTANCE;
    }

}
