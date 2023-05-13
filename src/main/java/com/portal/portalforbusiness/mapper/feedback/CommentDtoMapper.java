package com.portal.portalforbusiness.mapper.feedback;

import com.portal.portalforbusiness.dto.CommentDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.mapper.user.UserDtoMapper;
import com.portal.portalforbusiness.models.Comment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDtoMapper implements Mapper<CommentDto, Comment> {
    private static final CommentDtoMapper INSTANCE = new CommentDtoMapper();
    private final MarkDtoMapper markDtoMapper = MarkDtoMapper.getInstance();
    private final UserDtoMapper userDtoMapper = UserDtoMapper.getInstance();

    @Override
    public Comment mapFrom(CommentDto object) {
        Comment comment = new Comment();
        comment.setComment(object.getComment());
        comment.setId(object.getId());
        comment.setProductId(object.getProductId());
        comment.setUser(userDtoMapper.mapFrom(object.getUser()));
        comment.setCreated_at(object.getCreated_at());
//        comment.setUserId(object.getUserId());
        comment.setMarks(object.getMarks().stream().map(markDtoMapper::mapFrom).toList());

        return comment;
    }

    public static CommentDtoMapper getInstance() {return INSTANCE;}
}
