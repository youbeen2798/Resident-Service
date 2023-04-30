package com.nhnacademy.security.dto;

import com.nhnacademy.security.entity.Authority;
import java.time.LocalDateTime;

public interface ResidentDto {
  Integer getReidentSerialNumber();

  String getName();

  String getResidentRegistrationNumber();

  String getGenderCode();

  LocalDateTime getBirthDate();

  String getBirthPlaceCode();

  String getRegistrationBaseAddress();

  LocalDateTime getDeathDate();

  String getDeathPlaceCode();

  String getDeathPlaceAddress();

  String getLoginId();

  String getEmail();

  String getPassword();

  AuthorityDto getAuthority();

  interface AuthorityDto{
    String getAuthority();
  }

}
