package com.hami.ticketsystem.controller;

import com.hami.ticketsystem.auth.AuthRequest;
import com.hami.ticketsystem.auth.AuthResponse;
import com.hami.ticketsystem.auth.JwtTokenUtil;
import com.hami.ticketsystem.dto.SignUpDto;
import com.hami.ticketsystem.dto.UserDto;
import com.hami.ticketsystem.entity.User;
import com.hami.ticketsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "CRUD REST APIs for user Resource")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Login user")
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = (User) authenticate.getPrincipal();

            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @ApiOperation(value = "Signup")
    @PostMapping("/auth/signup")
    public ResponseEntity<SignUpDto> signUpUser(@RequestBody SignUpDto signUpDto) {
        SignUpDto user = userService.createUser(signUpDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update user")
    @PutMapping("/auth/{userId}/update")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long userId, SignUpDto signUpDto) {
        UserDto updateUser = userService.updateUser(userId, signUpDto);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Find user by id")
    @GetMapping("/find/{userId}")
    public ResponseEntity<UserDto> findUser(@PathVariable("userId") Long userId) {
        UserDto user = userService.findUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Find all users")
    @GetMapping("/findAllUser")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> allUser = userService.findAll();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user by id")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("Delete User Successfully", HttpStatus.OK);
    }

}
