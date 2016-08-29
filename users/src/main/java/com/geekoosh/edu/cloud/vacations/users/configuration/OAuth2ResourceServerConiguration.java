package com.geekoosh.edu.cloud.vacations.users.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.ws.rs.HttpMethod;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConiguration extends ResourceServerConfigurerAdapter {

    //oauth2 private key can be taken from vault at bootstrapping stage
    @Value("${config.oauth2.privateKey}")
    private String privateKey;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        return converter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user").hasRole("USER")
                .anyRequest().authenticated();
    }
}
