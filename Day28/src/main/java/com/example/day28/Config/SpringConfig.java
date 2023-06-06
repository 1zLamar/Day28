package com.example.day28.Config;

import com.example.day28.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvide() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvide())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/register").permitAll()
                .requestMatchers("/api/v1/user/login").permitAll()
                .requestMatchers("/api/v1/order/get","/api/v1/order/update/{id}","/api/v1/order/delete/{id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/order/add","/api/v1/order/get-id/{id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/order/change-status/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/user/update","/api/v1/user/delete","/api/v1/user/get-id/{id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/order/get","/api/v1/order/update/{id}","/api/v1/order/delete/{id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/product/get","/api/v1/product/add","/api/v1/product/update/{id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/product/delete/{id}","/api/v1/product/get-id/{id}").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}