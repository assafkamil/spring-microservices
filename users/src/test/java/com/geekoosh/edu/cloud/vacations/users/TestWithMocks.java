package com.geekoosh.edu.cloud.vacations.users;

import com.geekoosh.edu.cloud.vacations.sdk.PageableQuery;
import com.geekoosh.edu.cloud.vacations.users.model.User;
import com.geekoosh.edu.cloud.vacations.users.sdk.UserResponse;
import com.geekoosh.edu.cloud.vacations.users.sdk.UsersSDK;
import com.geekoosh.edu.cloud.vacations.users.services.UserService;
import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestWithMocks.class, Application.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayDBUnitTestExecutionListener.class
})
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class TestWithMocks {
    @Autowired
    private UsersSDK users;

    @Mock
    private UserService mockUserService;

    public TestWithMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Bean
    @Primary
    public UserService userService() {
        User user = new User();
        user.setId(1L);
        user.setUsername("useruser");
        Mockito.when(mockUserService.getUsers(Mockito.any())).thenReturn(Arrays.asList(user));

        return mockUserService;
    }

    @Test
    public void testMocks() {
        List<UserResponse> userResponses = users.users(new PageableQuery());
        assertEquals(userResponses.size(), 1);
        UserResponse userResponse = userResponses.get(0);
        assertEquals(userResponse.getUsername(), "useruser");
    }
}
