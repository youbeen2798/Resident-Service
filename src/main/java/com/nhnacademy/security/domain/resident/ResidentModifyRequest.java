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
public class ResidentModifyRequest {

  //이거 말고 다른거 바꾸고 싶으면?
  @NotEmpty
  private String name; //성명

}
