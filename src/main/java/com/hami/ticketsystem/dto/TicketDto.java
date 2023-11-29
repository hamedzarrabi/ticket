package com.hami.ticketsystem.dto;

import com.hami.ticketsystem.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ApiModel("Ticket")
public class TicketDto {
    @ApiModelProperty("Ticket Id")
    private Long id;
    @ApiModelProperty("Ticket Title")
    private String title;
    @ApiModelProperty("Ticket Id")
    private String status;
    @ApiModelProperty("Ticket Description")
    private String description;
    @ApiModelProperty("Ticket Comments")
    private Set<CommentDto> comments;

    public TicketDto(Long id, String title, String status, String description) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
    }
}
