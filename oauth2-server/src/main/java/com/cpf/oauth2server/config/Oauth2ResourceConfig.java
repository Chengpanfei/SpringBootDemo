package com.cpf.oauth2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ClassName      Oauth2ResourceConfig
 * Description    作为OAuth2资源服务器的配置，配置资源访问点
 *
 * @author CPF
 * @version 1.0
 * @date 2018/12/6 17:10
 */
@Configuration
@EnableResourceServer
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/oauth/user")
                .authorizeRequests().anyRequest().authenticated();
    }
}
