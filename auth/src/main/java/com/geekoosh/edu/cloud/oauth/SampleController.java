package com.geekoosh.edu.cloud.oauth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read')")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String hello2() {
        return "hello2";
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String hello3() {
        return "hello3";
    }


    @RequestMapping(value = "/bye/world", method = RequestMethod.GET)
    public String byeWorld() {
        return "bye";
    }
    @RequestMapping(value = "/bye/again", method = RequestMethod.GET)
    public String byeWorld2() {
        return "bye2";
    }
}
