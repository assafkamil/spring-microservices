package com.geekoosh.edu.cloud.vacations.users.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
