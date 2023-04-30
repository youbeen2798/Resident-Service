package com.nhnacademy.security.config;

import com.nhnacademy.security.auth.LoginSuccessHandler;
import com.nhnacademy.security.service.oauth.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    return http.authorizeRequests()
        .antMatchers("/residents/**").hasAuthority("ROLE_MEMBER")
        .anyRequest().permitAll()
        .and()
        .requiresChannel()
        .anyRequest().requiresInsecure() //HTTP 통신(모든 것들 http)
        .and()
        .formLogin()
          .usernameParameter("id")
          .passwordParameter("pwd")
          .loginPage("/auth/login")
          .loginProcessingUrl("/login")
          .successHandler(loginSuccessHandler())
        .and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/auth/login")
           .and()
        .csrf()
        .disable().build();
  }

  @Bean
  public LoginSuccessHandler loginSuccessHandler(){
    return new LoginSuccessHandler();
  }
  @Bean
  public AuthenticationProvider authenticationProvider(CustomUserDetailsService residentService) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //로그인 인증할 때 꼭 필요한 2가지
    //내부에서 알아서 비밀번호를 확인을 함(match 함수를 통해서 확인) - AuthenticationProvider가 함
    //인증을 하면 AuthenticationProvider
    authenticationProvider.setUserDetailsService(residentService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return authenticationProvider;
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


}


