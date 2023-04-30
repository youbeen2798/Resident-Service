package com.nhnacademy.security.domain.resident;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentInformationRequest {

  @NotEmpty
  private String id; //아이디

  @NotEmpty
  private String password; //비밀번호

  @NotEmpty
  private String email; //이메일
}
