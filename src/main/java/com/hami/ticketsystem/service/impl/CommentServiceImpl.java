package com.hami.ticketsystem.service.impl;

import com.hami.ticketsystem.dto.CommentDto;
import com.hami.ticketsystem.entity.Comment;
import com.hami.ticketsystem.entity.Ticket;
import com.hami.ticketsystem.exception.ResourceNotFoundException;
import com.hami.ticketsystem.exception.TicketSystemException;
import com.hami.ticketsystem.mapper.CommentMapper;
import com.hami.ticketsystem.repository.CommentRepository;
import com.hami.ticketsystem.repository.TicketRepository;
import com.hami.ticketsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.hami.ticketsystem.mapper.CommentMapper.mapToComment;
import static com.hami.ticketsystem.mapper.CommentMapper.mapToCommentDto;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public CommentDto createComment(Long ticketId, CommentDto commentDto) {

        Comment comment = mapToComment(commentDto);

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "id", ticketId)
        );

        comment.setTicket(ticket);

        Comment newComment = commentRepository.save(comment);

        return mapToCommentDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentByTicketId(Long ticketId) {
        List<Comment> comments = commentRepository.findByTicketId(ticketId);
        return comments.stream().map(
                CommentMapper::mapToCommentDto
        ).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long ticketId, Long commentId) {
        // retrieve ticket entity by id
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() ->
                new ResourceNotFoundException("Ticket", "id", ticketId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getTicket().getId().equals(ticket.getId())) {
            throw new TicketSystemException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(Long ticketId, Long commentId, CommentDto commentDto) {

        // retrieve ticket entity by id
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "id", ticketId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getTicket().getId().equals(ticket.getId())) {
            throw new TicketSystemException(HttpStatus.BAD_REQUEST, "Comment does not belongs to ticket");
        }

        comment.setComment(commentDto.getComment());

        Comment updateComment = commentRepository.save(comment);

        return mapToCommentDto(updateComment);
    }

    @Override
    public void deleteComment(Long commentId, Long ticketId) {
        // retrieve ticket entity by id
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "id", ticketId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getTicket().getId().equals(ticket.getId())) {
            throw new TicketSystemException(HttpStatus.BAD_REQUEST, "Comment does not belongs to ticket");
        }

        commentRepository.deleteById(comment.getId());
    }
}
