package com.nhnacademy.security.domain.householdMovementAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdMovementAddressModifyRequest {

  //전입주소
  public String houseMovementAddress;

  //최종주소여부
  public Character lastAddressYn;
}
