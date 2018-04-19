package com.adidas.cities.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/login", "/swagger-ui.html", "/webjars/**", "/images/**").permitAll() //We allow access to /login to anybody
            .anyRequest().authenticated() //Any other request requires authentication
            .and()
            // Request / login will come through this filter.
            .addFilterBefore(new LoginFilter("/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
                
            // Rest of the request will come through this filter.
            .addFilterBefore(new JwtFilter(),
                    UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Creates a default user account 
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}123abc")
                .roles("USER");
    }
}
