package com.nhnacademy.security.service.oauth;

import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import com.nhnacademy.security.service.resident.ResidentService;
import java.util.Collections;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


  private final ResidentRepository residentRepository;

  public CustomUserDetailsService(ResidentRepository residentRepository) {
    this.residentRepository = residentRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    ResidentDto residentDto = residentRepository.findByLoginId(username)
        .orElseThrow(() -> new ResidentNotFoundException());
    Integer birthDate = residentDto.getReidentSerialNumber();
    String password = residentDto.getPassword();

    return new User(residentDto.getLoginId(), residentDto.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
  }//$2a$10$AL3dPriU/Axf2hTj87vOWu8eQYQF0pO3/XJddjObkJ.5.LxVflIce

}
