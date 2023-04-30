package com.nhnacademy.security.dto;


import com.nhnacademy.security.entity.Household;
import com.nhnacademy.security.entity.HouseholdMovementAddress;

public interface HouseholdMovementAddressDto {

  HouseholdMovementAddress.Pk getPk();

  Household getHousehold();

  String getHouseMovementAddress();

  Character getLastAddressYn();
}
