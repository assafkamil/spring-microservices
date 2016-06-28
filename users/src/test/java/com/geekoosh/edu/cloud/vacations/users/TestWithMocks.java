package com.geekoosh.edu.cloud.vacations.users;

import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestSDK.class, Application.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayDBUnitTestExecutionListener.class
})
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class TestWithMocks {
    @Test
    public void testMocks() {
        
    }
}
