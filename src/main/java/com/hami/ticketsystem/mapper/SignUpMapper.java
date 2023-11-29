package com.hami.ticketsystem.mapper;

import com.hami.ticketsystem.dto.SignUpDto;
import com.hami.ticketsystem.entity.User;

public class SignUpMapper {

    public static SignUpDto mapToSignUp(User user) {
        return new SignUpDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User mapToUserSignUp(SignUpDto signUpDto) {
        return new User(
                signUpDto.getFirstName(),
                signUpDto.getLastName(),
                signUpDto.getEmail(),
                signUpDto.getPassword()
        );
    }

}
