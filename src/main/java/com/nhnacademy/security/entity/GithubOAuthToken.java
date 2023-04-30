package com.nhnacademy.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GithubOAuthToken {

  private String access_token;
  private String expires_in;
  private String scope;
  private String token_type;
}
