package com.nhnacademy.security.controller;

import com.nhnacademy.security.dto.ResidentResponse;
import com.nhnacademy.security.entity.Authority;
import com.nhnacademy.security.entity.GithubOAuthToken;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import com.nhnacademy.security.service.oauth.OAuthService;
import com.nhnacademy.security.service.resident.ResidentService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OAuth2Controller{

  private final OAuthService oAuthService;
  private final ResidentService residentService;
  private String clientId = "14e3a2433abd30b085d7";

  public OAuth2Controller(OAuthService oAuthService, ResidentService residentService) {
    this.oAuthService = oAuthService;
    this.residentService = residentService;
  }

  @GetMapping("/login")
  public void doGithub( HttpServletResponse response) throws IOException {
    response.sendRedirect("https://github.com/login/oauth/authorize?client_id=14e3a2433abd30b085d7&redirect_url=http://localhost:8080/login/oauth2/code/github");
  }

  @GetMapping("/login/oauth2/code/github")
  public void redirectGithub( @RequestParam("code") String code, HttpServletResponse resp)
      throws IOException {

    GithubOAuthToken token = oAuthService.sendInformation(code, clientId);

    String email = oAuthService.getEmail(token);

    ResidentResponse residentResponse = residentService.getResidentByEmail(email);

    //이메일에 없는 값이 들어오면 다시 login 페이지로 redirect 시키기
    if(residentResponse == null){
      resp.sendRedirect("/auth/login");
    }

    Authority authority = new Authority();
    authority.setAuthority(residentResponse.getAuthority());

    UserDetails userDetails = new User(residentResponse.getLoginId(), residentResponse.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(
        authority.getAuthority())));

    oAuthService.putSession(userDetails);

    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, residentResponse.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(authority.getAuthority())));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    resp.sendRedirect("/residents/" + residentResponse.getReidentSerialNumber());


  }

}

