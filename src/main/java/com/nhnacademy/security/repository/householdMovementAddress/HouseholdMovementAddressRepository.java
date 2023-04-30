package com.nhnacademy.security.repository.householdMovementAddress;

import com.nhnacademy.security.dto.HouseholdMovementAddressDto;
import com.nhnacademy.security.entity.HouseholdMovementAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk>, HouseholdMovementAddressRepositoryCustom {

  Optional<HouseholdMovementAddressDto> findByPk(HouseholdMovementAddress.Pk pk);

  List<HouseholdMovementAddressDto> findAllByHousehold_HouseholdSerialNumber(Integer householdSerialNumber);

}
