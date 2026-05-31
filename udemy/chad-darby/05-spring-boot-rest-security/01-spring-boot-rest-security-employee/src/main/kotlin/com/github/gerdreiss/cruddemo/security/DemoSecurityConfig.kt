package com.github.gerdreiss.cruddemo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class DemoSecurityConfig {

    @Bean
    fun inMemoryUserManager(): InMemoryUserDetailsManager =
        InMemoryUserDetailsManager(
            User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build(),
            User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build(),
            User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build()
        )


    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain? =
        httpSecurity
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            }
            // use HTTP Basic authentication
            .httpBasic(Customizer.withDefaults())
            // disable Cross Site Request Forgery (CSRF)
            // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
            .csrf { it.disable() }
            .build()

}