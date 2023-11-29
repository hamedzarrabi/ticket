package com.hami.ticketsystem.service;

import com.hami.ticketsystem.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long ticketId, CommentDto commentDto);
    List<CommentDto> getCommentByTicketId(Long ticketId);
    CommentDto getCommentById(Long ticketId, Long commentId);
    CommentDto updateComment(Long ticketId, Long commentId, CommentDto commentDto);
    void deleteComment(Long ticketId, Long commentId);

}
