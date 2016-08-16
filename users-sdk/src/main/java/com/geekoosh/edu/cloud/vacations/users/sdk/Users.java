package com.geekoosh.edu.cloud.vacations.users.sdk;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface Users {
    @RequestMapping(name="/user", method = RequestMethod.POST)
    UserResponse newUser(@RequestBody CreateUserRequest createUserRequest) throws UserExistsException;

    @RequestMapping(name="/user", method = RequestMethod.GET)
    List<UserResponse> users(Pageable pageable);
}
