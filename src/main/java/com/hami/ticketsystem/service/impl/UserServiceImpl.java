package com.hami.ticketsystem.service.impl;

import com.hami.ticketsystem.dto.SignUpDto;
import com.hami.ticketsystem.dto.UserDto;
import com.hami.ticketsystem.entity.Role;
import com.hami.ticketsystem.entity.User;
import com.hami.ticketsystem.exception.ResourceNotFoundException;
import com.hami.ticketsystem.mapper.UserMapper;
import com.hami.ticketsystem.repository.UserRepository;
import com.hami.ticketsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hami.ticketsystem.mapper.SignUpMapper.mapToSignUp;
import static com.hami.ticketsystem.mapper.UserMapper.mapToUser;
import static com.hami.ticketsystem.mapper.UserMapper.mapToUserDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private User user;

    @Override
    public SignUpDto createUser(SignUpDto signUpDto) {
        Optional<User> byEmail = userRepository.findByEmail(signUpDto.getEmail());

        if (byEmail.isPresent()) {
            throw new UsernameNotFoundException("Email is already taken!");
        } else {
            user = new User();
            Integer  r = 3; // Default ROLE_CUSTOMER

            // convert DTO to entity
            user.setFirstName(signUpDto.getFirstName());
            user.setLastName(signUpDto.getLastName());
            user.setEmail(signUpDto.getEmail());
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword())); // encode password
            user.addRole(new Role(r));

            User saveUser = userRepository.save(user); // save user in database

            return mapToSignUp(saveUser);
        }
    }

    @Override
    public UserDto updateUser(Long userId, SignUpDto signUpDto) {

        Optional<User> userFind = userRepository.findById(userId);
        if (userFind.isPresent()) {
            user = new User();

            // convert DTO to entity
            user.setId(userFind.get().getId());
            user.setFirstName(signUpDto.getFirstName());
            user.setLastName(signUpDto.getLastName());
            user.setEmail(signUpDto.getEmail());
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword())); // encode password


            User saveUser = userRepository.save(user); // update user in database

            return mapToUserDto(saveUser);
        } else {
            throw new UsernameNotFoundException("User is already taken!");
        }

    }

    @Override
    public List<UserDto> findAll() {

        List<User> allUsers = userRepository.findAll();

        return allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findUser(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        return mapToUserDto(findUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
