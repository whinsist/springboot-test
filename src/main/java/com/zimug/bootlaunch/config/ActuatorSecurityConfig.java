package com.zimug.bootlaunch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Hong.Wu
 * @date: 11:53 2021/02/13
 */

@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * configure(HttpSecurity)方法定义了哪些URL路径应该被保护
     * https://blog.csdn.net/niugang0920/article/details/79756977
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/actuator/*").hasRole("ACTUATOR_ADMIN")
                .antMatchers("/actuator/*").authenticated();

    }
}
