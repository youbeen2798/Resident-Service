package com.nhnacademy.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ResidentResponse {

  private Integer reidentSerialNumber;

  private String loginId; 

  private String password;

  private String email;

  private String authority;
}


