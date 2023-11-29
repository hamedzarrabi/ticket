package com.hami.ticketsystem.service;

import com.hami.ticketsystem.dto.SignUpDto;
import com.hami.ticketsystem.dto.UserDto;
import com.hami.ticketsystem.entity.User;

import java.util.List;

public interface UserService {
    SignUpDto createUser(SignUpDto signUpDto);
    UserDto updateUser(Long userId, SignUpDto signUpDto);
    List<UserDto> findAll();
    UserDto findUser(Long userId);
    void deleteUser(Long userId);
}
