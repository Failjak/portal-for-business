package com.portal.portalforbusiness.mapper.feedback;

import com.portal.portalforbusiness.dto.CommentDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.mapper.user.UserMapper;
import com.portal.portalforbusiness.models.Comment;


public class CommentMapper implements Mapper<Comment, CommentDto> {
    private static final CommentMapper INSTANCE = new CommentMapper();
    private final MarkMapper markMapper = MarkMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();


    @Override
    public CommentDto mapFrom(Comment object) {
        return CommentDto.builder().
                id(object.getId()).
                comment(object.getComment()).
                user(userMapper.mapFrom(object.getUser())).
                created_at(object.getCreated_at()).
//                userId(object.getUserId()).
                productId(object.getProductId()).
                marks(object.getMarks().stream().map(markMapper::mapFrom).toList()).
                build();
    }

    public static CommentMapper getInstance() {
        return INSTANCE;
    }
}
