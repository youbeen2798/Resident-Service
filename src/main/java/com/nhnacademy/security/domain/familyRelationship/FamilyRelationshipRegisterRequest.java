package com.nhnacademy.security.domain.familyRelationship;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipRegisterRequest {

      // 가족 주민 일련번호
      @NotNull
      private Integer familySerialNumber;

      //관계 : 부, 모
      @NotEmpty
      private String relationShip;

}

