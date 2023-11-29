package com.hami.ticketsystem.dto;

import com.hami.ticketsystem.entity.Ticket;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ApiModel("Comment")
public class CommentDto {
    @ApiModelProperty(value = "Comment ID")
    private Long id;
    @ApiModelProperty(value = "Comment")
    private String comment;
}
