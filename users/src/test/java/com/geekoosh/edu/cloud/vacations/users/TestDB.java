package com.geekoosh.edu.cloud.vacations.users;

import com.geekoosh.edu.cloud.vacations.users.model.Order;
import com.geekoosh.edu.cloud.vacations.users.model.Preference;
import com.geekoosh.edu.cloud.vacations.users.model.User;
import com.geekoosh.edu.cloud.vacations.users.services.UserService;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.dbunit.DBUnitSupport;
import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayDBUnitTestExecutionListener.class
})
public class TestDB {

    @Autowired
    private UserService userService;

    @Test
    @FlywayTest
    @DBUnitSupport(loadFilesForRun = { "INSERT", "/dbunit/users.xml"})
    public void testUser() {
        User user = userService.userById(5L);
        assertEquals((long)user.getId(), 5L);
        assertEquals(user.getUsername(), "david");
    }

    @Test
    @FlywayTest
    @DBUnitSupport(loadFilesForRun = { "INSERT", "/dbunit/users.xml"})
    public void testOrder() {
        User user = userService.userById(5L);
        Order order = userService.createOrder(user.getId(), "my info");

        List<Order> orders = userService.getUserOrders(user.getId());
        assertEquals(orders.size(), 1);
        assertEquals(orders.get(0).getDetails(), "my info");
    }

    @Test
    @FlywayTest
    @DBUnitSupport(loadFilesForRun = { "INSERT", "/dbunit/users.xml"})
    public void testPreferences() {
        User user = userService.userById(5L);
        Long userId = 5L;
        Long preferenceId = 3L;
        userService.setUserPreference(userId, preferenceId);

        List<Preference> preferences = userService.getUserPreferences(userId);
        assertEquals(preferences.size(), 1);
        assertEquals(preferences.get(0).getPreferenceType(), "Sun and beach");
    }
}
