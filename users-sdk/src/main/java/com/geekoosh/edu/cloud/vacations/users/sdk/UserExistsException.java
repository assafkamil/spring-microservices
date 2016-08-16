package com.geekoosh.edu.cloud.vacations.users.sdk;

public class UserExistsException extends Exception {
    public UserExistsException(String username, Throwable inner) {
        super(inner);
    }
}
