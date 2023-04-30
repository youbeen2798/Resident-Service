package com.nhnacademy.security.service.householdMovementAddress;

import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressModifyRequest;
import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressRegisterRequest;
import com.nhnacademy.security.dto.HouseholdMovementAddressDto;
import java.time.LocalDate;
import java.util.List;

public interface HouseholdMovementAddressService {

  void create(Integer householdSerialNumber, HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest);

  void modify(Integer householdSerialNumber, LocalDate reportDate, HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest);

  void delete(Integer householdSerialNumber, LocalDate reportDate);

  List<HouseholdMovementAddressDto> getAllByHouseholdResidentSerialNumber(Integer householdSerialNumber);

}
