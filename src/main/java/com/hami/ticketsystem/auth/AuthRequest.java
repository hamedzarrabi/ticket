package com.hami.ticketsystem.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @Email @Length(min = 5, max = 50)
    private String email;
    @Length(min = 5, max = 15)
    private String password;
}
