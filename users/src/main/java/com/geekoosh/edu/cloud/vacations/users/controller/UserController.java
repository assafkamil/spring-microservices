package com.geekoosh.edu.cloud.vacations.users.controller;

import com.geekoosh.edu.cloud.vacations.users.model.User;
import com.geekoosh.edu.cloud.vacations.users.sdk.CreateUserRequest;
import com.geekoosh.edu.cloud.vacations.users.sdk.UserResponse;
import com.geekoosh.edu.cloud.vacations.users.sdk.UsersSDK;
import com.geekoosh.edu.cloud.vacations.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController implements UsersSDK {

    private Logger logger;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponse newUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.newUser(createUserRequest);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> users(@PageableDefault(value=20) Pageable pageable) {
        List<User> users = userService.getUsers(pageable);
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

}
