package com.portal.portalforbusiness.services;

import com.portal.portalforbusiness.dao.feedback.CommentDaoImpl;
import com.portal.portalforbusiness.dto.CommentDto;
import com.portal.portalforbusiness.dto.ProductDto;
import com.portal.portalforbusiness.mapper.feedback.CommentDtoMapper;
import com.portal.portalforbusiness.mapper.feedback.CommentMapper;
import com.portal.portalforbusiness.models.Comment;


import java.util.List;
import java.util.Optional;

public class CommentService implements Service {
    private static final CommentService INSTANCE = new CommentService();
    private final MarkService markService = MarkService.getInstance();
    private final CommentDaoImpl commentDao = new CommentDaoImpl();
    private final CommentMapper commentMapper = CommentMapper.getInstance();
    private final CommentDtoMapper commentDtoMapper = CommentDtoMapper.getInstance();

    public void save(CommentDto commentDto) {
        Optional<Comment> comment = commentDao.save(commentDtoMapper.mapFrom(commentDto));
        if(comment.isPresent())
            markService.save(commentDto.getMarks(), comment.get());
    }

    public Optional<List<CommentDto>>  getAllCommentsByProduct(ProductDto product) {
        return commentDao.getAllCommentsByProductId(product.getId()).map(comments -> comments.stream().map(commentMapper::mapFrom).toList());
    }

    public static CommentService getInstance() {return INSTANCE;}
}
