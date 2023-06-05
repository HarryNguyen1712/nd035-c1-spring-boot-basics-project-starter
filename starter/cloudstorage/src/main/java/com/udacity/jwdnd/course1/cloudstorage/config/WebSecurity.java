package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
                                         AuthenticationProvider authenticationProvider)
      throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authenticationProvider(authenticationProvider)
        .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/user/signup", "/login", "/css/**", "/js/**").permitAll()
            .requestMatchers("/web/**","/home").authenticated()
        )
        .formLogin(
            httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login")
                .passwordParameter("password").usernameParameter("username")
                .defaultSuccessUrl("/home", true))
        .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/logout"))
    ;
    return http.build();
  }
}
