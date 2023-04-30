package com.nhnacademy.security.repository.household;

import com.nhnacademy.security.dto.HouseholdDto;
import com.nhnacademy.security.entity.Household;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long>, HouseholdRepositoryCustom {

  HouseholdDto findByHouseholdSerialNumber(Integer householdSerialNumber);

  List<HouseholdDto> findAllByResident_ReidentSerialNumber(Integer residentSerialNumber);
}
