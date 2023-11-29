package com.hami.ticketsystem.mapper;

import com.hami.ticketsystem.dto.CommentDto;
import com.hami.ticketsystem.entity.Comment;

public class CommentMapper {

    public static CommentDto mapToCommentDto(Comment comment) {

        return new CommentDto(
                comment.getId(),
                comment.getComment()
        );
    }

    public static Comment mapToComment(CommentDto commentDto) {

        return new Comment(
                commentDto.getId(),
                commentDto.getComment()
        );
    }
}
