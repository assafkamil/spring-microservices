package com.geekoosh.edu.cloud.vacations.users.controller;

import com.geekoosh.edu.cloud.vacations.users.model.User;
import com.geekoosh.edu.cloud.vacations.users.sdk.CreateUserRequest;
import com.geekoosh.edu.cloud.vacations.users.sdk.UserExistsException;
import com.geekoosh.edu.cloud.vacations.users.sdk.UserResponse;
import com.geekoosh.edu.cloud.vacations.users.sdk.Users;
import com.geekoosh.edu.cloud.vacations.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController implements Users {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponse newUser(@RequestBody CreateUserRequest createUserRequest) throws UserExistsException {
        logger.info("creating new user of username: {}", createUserRequest.getUsername());
        User user = userService.newUser(createUserRequest);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> users(@PageableDefault(value=20) Pageable pageable) {
        logger.info("users");
        List<User> users = userService.getUsers(pageable);
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistsException.class)
    public void userExistsHandler(UserExistsException e) {

    }

}
