package com.nhnacademy.security.domain.familyRelationship;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipModifyRequest {

  //관계 : 부, 모
  //바뀔 수 있는 필드가 하나이므로 valid 걸어줌
  @NotEmpty
  private String relationShip;

}
