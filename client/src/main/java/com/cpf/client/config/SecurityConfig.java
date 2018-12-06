package com.cpf.client.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classname SecurityConfig
 * <p>
 * spring security configuration.
 * 主要用来配置认证策略（inMemory，jdbc，自定义，LDAP，ACL等）
 * 以及路由保护（指定url需要什么权限，角色等）
 *
 * @author CPF
 * @version 1.0
 * @date 2018/11/28 10:10
 */

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置需要认证的url，需要的权限，身份等
     * formLogin 配置通过表单登录认证
     * httpBasic 配置Basic认证
     * authenticated 要求登录
     * hasRole不禁要求登录还要求有相应身份
     * 定制authenticationProvider实现jwt，oauth2等登录方式
     * 是通过filter实现的
     *
     * @param http http
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/")
                .hasAnyAuthority("Read Post");

    }

    /**
     * 通过builder指定认证方式，inMemory，jdbc，LDAP，自定义等
     * 指定用户密码的来源，密码加密方式等，默认使用httpBasic
     *
     * @param auth builder
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
