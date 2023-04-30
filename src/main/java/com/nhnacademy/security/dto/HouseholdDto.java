package com.nhnacademy.security.dto;

import com.nhnacademy.security.entity.Resident;
import java.time.LocalDate;

public interface HouseholdDto {

  Integer getHouseholdSerialNumber();

  Resident getResident();

  LocalDate getHouseholdCompositionDate();

  String getHouseholdCompositionReasonCode();

  String getCurrentHouseMovementAddress();
}
