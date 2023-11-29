package com.hami.ticketsystem.controller;

import com.hami.ticketsystem.dto.CommentDto;
import com.hami.ticketsystem.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(value = "CRUD Rest APIs for Ticket resource")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Create Comment REST API")
    @PostMapping("/ticket/{ticketId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("ticketId") Long ticketId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto newComment = commentService.createComment(ticketId, commentDto);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER", "ROLE_SUPPORT"})
    @ApiOperation(value = "getCommentByTicketId Comment REST API")
    @GetMapping("/ticket/comments/{ticketId}")
    public ResponseEntity<List<CommentDto>> getCommentByTicketId(@PathVariable("ticketId") Long ticketId) {
        List<CommentDto> commentByTicketId = commentService.getCommentByTicketId(ticketId);
        return new ResponseEntity<>(commentByTicketId, HttpStatus.OK);
    }

    @RolesAllowed("ROLE_CUSTOMER")
    @ApiOperation(value = "getCommentId Comment REST API")
    @GetMapping("ticket/{ticketId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentId(@PathVariable("ticketId") Long ticketId,
                                                   @PathVariable("commentId") Long commentId) {
        CommentDto commentById = commentService.getCommentById(ticketId, commentId);
        return new ResponseEntity<>(commentById, HttpStatus.OK);
    }

//    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @ApiOperation(value = "updateComment Comment REST API")
    @PutMapping("/{ticketId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("ticketId") Long ticketId,
                                                    @PathVariable("commentId") Long commentId,
                                                    CommentDto commentDto) {
        CommentDto updateComment = commentService.updateComment(ticketId, commentId, commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @ApiOperation(value = "deleteComment Comment REST API")
//    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{commentId}/ticket/{ticketId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Long commentId,
                                                @PathVariable("ticketId") Long ticketId) {
        commentService.deleteComment(commentId, ticketId);

        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
