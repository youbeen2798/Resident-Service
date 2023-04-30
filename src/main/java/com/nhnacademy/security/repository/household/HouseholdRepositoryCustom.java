package com.nhnacademy.security.repository.household;

import com.nhnacademy.security.domain.household.HouseholdRegisterRequest;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdRepositoryCustom {

  void create(HouseholdRegisterRequest householdRegisterRequest);

  void delete(Integer householdSerialNumber);


}
