package com.nhnacademy.security.service.household;


import com.nhnacademy.security.domain.household.HouseholdRegisterRequest;
import com.nhnacademy.security.dto.HouseholdDto;
import com.nhnacademy.security.repository.household.HouseholdRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("HouseholdService")
public class HouseholdServiceImpl implements HouseholdService{

  private final HouseholdRepository householdRepository;

  public HouseholdServiceImpl(HouseholdRepository householdRepository) {
    this.householdRepository = householdRepository;
  }

  @Override
  public void create(HouseholdRegisterRequest householdRegisterRequest) {
    householdRepository.create(householdRegisterRequest);
  }

  @Override
  public void delete(Integer householdSerialNumber) {
    householdRepository.delete(householdSerialNumber);
  }

  @Override
  public List<HouseholdDto> getAllByHouseholdResidentSerialNumber(Integer householdSerialNumber) {
    return householdRepository.findAllByResident_ReidentSerialNumber(householdSerialNumber);
  }
}
