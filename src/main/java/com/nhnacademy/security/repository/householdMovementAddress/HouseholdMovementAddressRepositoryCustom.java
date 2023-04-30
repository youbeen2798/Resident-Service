package com.nhnacademy.security.repository.householdMovementAddress;

import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressModifyRequest;
import com.nhnacademy.security.entity.HouseholdMovementAddress;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {

  void updateLastAddress(Integer householdSerialNumber);

  Long modify(HouseholdMovementAddress.Pk pk, HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest);

  void delete(HouseholdMovementAddress.Pk pk);

}
