package com.geekoosh.edu.cloud.vacations.users;

import com.geekoosh.edu.cloud.vacations.sdk.PageableQuery;
import com.geekoosh.edu.cloud.vacations.tests.TestRibbonConfig;
import com.geekoosh.edu.cloud.vacations.users.sdk.CreateUserRequest;
import com.geekoosh.edu.cloud.vacations.users.sdk.UserResponse;
import com.geekoosh.edu.cloud.vacations.users.sdk.UsersSDK;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestSDK.class, Application.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayDBUnitTestExecutionListener.class
})
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class TestSDK extends TestRibbonConfig {
    @Autowired
    private UsersSDK users;

    @Test
    @FlywayTest
    public void testInsert() {
        UserResponse userResponse = users.newUser(new CreateUserRequest("userA"));
        assertEquals(userResponse.getUsername(), "userA");
        assertEquals((long)userResponse.getId(), 1L);

        List<UserResponse> usersResponse = users.users(new PageableQuery(5));
        assertEquals(usersResponse.size(), 1);

        users.newUser(new CreateUserRequest("userA1"));
        users.newUser(new CreateUserRequest("userA2"));
        users.newUser(new CreateUserRequest("userA3"));
        users.newUser(new CreateUserRequest("userA4"));
        users.newUser(new CreateUserRequest("userA5"));

        usersResponse = users.users(new PageableQuery(5));
        assertEquals(usersResponse.size(), 5);

        usersResponse = users.users(new PageableQuery(5, 5));
        assertEquals(usersResponse.size(), 1);
    }
}
