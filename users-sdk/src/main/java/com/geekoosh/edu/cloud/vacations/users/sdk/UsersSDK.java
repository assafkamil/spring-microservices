package com.geekoosh.edu.cloud.vacations.users.sdk;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("users")
public interface UsersSDK extends Users {
}
