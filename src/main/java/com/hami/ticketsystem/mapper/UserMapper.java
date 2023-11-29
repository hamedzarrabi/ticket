package com.hami.ticketsystem.mapper;

import com.hami.ticketsystem.dto.UserDto;
import com.hami.ticketsystem.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }
}
