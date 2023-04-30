package com.nhnacademy.security.service.household;

import com.nhnacademy.security.domain.household.HouseholdRegisterRequest;
import com.nhnacademy.security.dto.HouseholdDto;
import java.util.List;

public interface HouseholdService {

  void create(HouseholdRegisterRequest householdRegisterRequest);

  void delete(Integer householdSerialNumber);

  List<HouseholdDto> getAllByHouseholdResidentSerialNumber(Integer householdSerialNumber);

}
