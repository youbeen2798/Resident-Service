package com.nhnacademy.security.service.householdMovementAddress;


import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressModifyRequest;
import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressRegisterRequest;
import com.nhnacademy.security.dto.HouseholdDto;
import com.nhnacademy.security.dto.HouseholdMovementAddressDto;
import com.nhnacademy.security.entity.Household;
import com.nhnacademy.security.entity.HouseholdMovementAddress;
import com.nhnacademy.security.entity.HouseholdMovementAddress.Pk;
import com.nhnacademy.security.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.security.exception.HouseholdNotFoundException;
import com.nhnacademy.security.repository.household.HouseholdRepository;
import com.nhnacademy.security.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("HouseholdMovementAddressService")
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService{
  private final HouseholdMovementAddressRepository householdMovementAddressRepository;
  private final HouseholdRepository householdRepository;

  public HouseholdMovementAddressServiceImpl(HouseholdMovementAddressRepository householdMovementAddressRepository,
      HouseholdRepository householdRepository) {
    this.householdMovementAddressRepository = householdMovementAddressRepository;
    this.householdRepository = householdRepository;
  }

  @Override
  @Transactional
  public void create(Integer householdSerialNumber,
      HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest) {

    HouseholdMovementAddress.Pk pk = new Pk(householdMovementAddressRegisterRequest.getHouseMovementReportDate(),
        householdSerialNumber);

    HouseholdDto householdDto = householdRepository.findByHouseholdSerialNumber(householdSerialNumber);

    if(householdDto == null){
      throw new HouseholdNotFoundException();
    }
    Household household = householdDtoToHousehold(householdDto);

    HouseholdMovementAddress householdMovementAddress = HouseholdMovementAddress.builder()
            .pk(pk)
            .household(household)
            .houseMovementAddress(householdMovementAddressRegisterRequest.getHouseMovementAddress())
            .lastAddressYn(householdMovementAddressRegisterRequest.getLastAddressYn())
            .build();

    //지금 신고된 전입주소가 최종주소라면
    if(householdMovementAddressRegisterRequest.getLastAddressYn().equals('Y')){
      householdMovementAddressRepository.updateLastAddress(householdSerialNumber);
    }
    //household도 바꿔주기(수정해야함)
    householdMovementAddressRepository.save(householdMovementAddress);
  }

  @Transactional
  @Override
  public void modify(Integer householdSerialNumber,
      LocalDate reportDate,
      HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest) {
    //세대일련번호, 전입주소, 최종주소여부

    HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(reportDate, householdSerialNumber);

    //수정하려는 세대전입주소가 존재하지 않는다면 에러
    householdMovementAddressRepository.findByPk(pk).orElseThrow(
        HouseholdMovementAddressNotFoundException::new);

    householdMovementAddressRepository.modify(pk, householdMovementAddressModifyRequest);
  }

  @Transactional
  @Override
  public void delete(Integer householdSerialNumber,
      LocalDate reportDate) {

    HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(reportDate, householdSerialNumber);

    //수정하려는 세대전입주소가 존재하지 않는다면 에러
    householdMovementAddressRepository.findByPk(pk).orElseThrow(HouseholdMovementAddressNotFoundException::new);

    householdMovementAddressRepository.delete(pk);
  }

  @Override
  public List<HouseholdMovementAddressDto> getAllByHouseholdResidentSerialNumber(Integer householdSerialNumber) {
    return householdMovementAddressRepository.findAllByHousehold_HouseholdSerialNumber(householdSerialNumber);
  }


  public Household householdDtoToHousehold(HouseholdDto householdDto){
    return Household.builder()
        .householdSerialNumber(householdDto.getHouseholdSerialNumber())
        .resident(householdDto.getResident())
        .householdCompositionDate(householdDto.getHouseholdCompositionDate())
        .householdCompositionReasonCode(householdDto.getHouseholdCompositionReasonCode())
        .currentHouseMovementAddress(householdDto.getCurrentHouseMovementAddress())
        .build();
  }
}
