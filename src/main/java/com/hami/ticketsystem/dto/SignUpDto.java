package com.hami.ticketsystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Signup")
public class SignUpDto {
    @ApiModelProperty("User FirstName")
    private String firstName;
    @ApiModelProperty("User Last Name")
    private String lastName;
    @ApiModelProperty("User Email")
    private String email;
    @ApiModelProperty("User Password")
    private String password;
}
