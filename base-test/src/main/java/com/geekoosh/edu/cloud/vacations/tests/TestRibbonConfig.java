package com.geekoosh.edu.cloud.vacations.tests;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

public class TestRibbonConfig {
    @Autowired
    Environment environment;

    @Bean
    public ServerList<Server> ribbonServerList() {
        ServerList<Server> list = new ServerList<Server>() {
            @Override
            public List<Server> getInitialListOfServers() {
                return Arrays.asList(new Server("localhost", Integer.parseInt(environment.getProperty("local.server.port"))));
            }

            @Override
            public List<Server> getUpdatedListOfServers() {
                return Arrays.asList(new Server("localhost", Integer.parseInt(environment.getProperty("local.server.port"))));
            }
        };
        return list;
    }
}
