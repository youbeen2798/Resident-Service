package com.nhnacademy.security.auth;

import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.service.resident.ResidentService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements
    AuthenticationSuccessHandler
{

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ResidentService residentService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
  // principal이 userDetails, Credentials가 비밀번호, Authorities가 권한

    String sessionId = UUID.randomUUID().toString();

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    String authority = new ArrayList<>(userDetails.getAuthorities()).get(0).getAuthority();

    redisTemplate.opsForHash().put(sessionId, "username", username);
    redisTemplate.opsForHash().put(sessionId, "authority", authority);

    //loginId로 resident 객체 꺼내오기
    ResidentDto residentDto = residentService.getResident(userDetails.getUsername());
    Integer residentSerialNumber = residentDto.getReidentSerialNumber(); //주민일련번호

    response.sendRedirect("/residents/" + residentSerialNumber);
  }
}