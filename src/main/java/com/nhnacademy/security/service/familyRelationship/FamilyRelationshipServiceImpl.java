package com.nhnacademy.security.service.familyRelationship;


import com.nhnacademy.security.domain.familyRelationship.FamilyRelationshipModifyRequest;
import com.nhnacademy.security.domain.familyRelationship.FamilyRelationshipRegisterRequest;
import com.nhnacademy.security.dto.FamilyRelationshipDto;
import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.entity.FamilyRelationship;
import com.nhnacademy.security.entity.FamilyRelationship.Pk;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.FamilyRelationshipAlreadyExistException;
import com.nhnacademy.security.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.nhnacademy.security.repository.familyRelationship.FamilyRelationshipRelationshipRepository;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("familyRelationshipService")
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService{

  private final FamilyRelationshipRelationshipRepository familyRelationshipRepository;
  private final ResidentRepository residentRepository;

  public FamilyRelationshipServiceImpl(FamilyRelationshipRelationshipRepository familyRelationshipRepository,
      ResidentRepository residentRepository) {
    this.familyRelationshipRepository = familyRelationshipRepository;
    this.residentRepository = residentRepository;
  }

  @Transactional
  @Override
  public void create(Integer residentSerialNumber, FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest) {

    ResidentDto residentDto= residentRepository.findByReidentSerialNumber(residentSerialNumber);

    if(residentDto == null){ //주민이 없는데 가족관계를 만들려 한다면
      throw new ResidentNotFoundException();
    }

    Resident resident = Resident.builder()
        .reidentSerialNumber(residentDto.getReidentSerialNumber())
        .name(residentDto.getName())
        .residentRegistrationNumber(residentDto.getResidentRegistrationNumber())
        .genderCode(residentDto.getGenderCode())
        .birthDate(residentDto.getBirthDate())
        .birthPlaceCode(residentDto.getBirthPlaceCode())
        .registrationBaseAddress(residentDto.getRegistrationBaseAddress())
        .deathDate(residentDto.getDeathDate())
        .deathPlaceAddress(residentDto.getDeathPlaceAddress())
        .email(residentDto.getEmail())
        .loginId(residentDto.getLoginId())
        .password(residentDto.getPassword())
        .build();

    FamilyRelationship.Pk pk = new Pk(familyRelationshipRegisterRequest.getFamilySerialNumber(), residentSerialNumber);

    FamilyRelationshipDto familyRelationshipDto = familyRelationshipRepository.findByPk(pk);

    //만약 가족 관계가 이미 존재한다면
    if(familyRelationshipDto != null){
      throw new FamilyRelationshipAlreadyExistException();
    }

    FamilyRelationship familyRelationship = FamilyRelationship.builder()
        .pk(pk)
        .resident(resident)
        .familyRelationshipCode(familyRelationshipRegisterRequest.getRelationShip())
        .build();

    familyRelationshipRepository.save(familyRelationship);
  }

  @Transactional
  @Override
  public void modify(Integer residentSerialNumber,
      Integer familySerialNumber,
      FamilyRelationshipModifyRequest familyRelationshipModifyRequest) {

    FamilyRelationship.Pk pk = new Pk(familySerialNumber, residentSerialNumber);

    //수정하려는 가족관계가 존재하지 않는다면
    FamilyRelationshipDto familyRelationshipDto = familyRelationshipRepository.findByPk(pk);

    if(familyRelationshipDto == null){
      throw new FamilyRelationshipNotFoundException();
    }
    ResidentDto residentDto= residentRepository.findByReidentSerialNumber(residentSerialNumber);

    //가족관계를 수정하려하는데 주민이 존재하지 않는다면
    if(residentDto == null){
      throw new ResidentNotFoundException();
    }

    //dto를 주민으로 변환
    Resident resident = Resident.builder()
        .reidentSerialNumber(residentDto.getReidentSerialNumber())
        .name(residentDto.getName())
        .residentRegistrationNumber(residentDto.getResidentRegistrationNumber())
        .genderCode(residentDto.getGenderCode())
        .birthDate(residentDto.getBirthDate())
        .birthPlaceCode(residentDto.getBirthPlaceCode())
        .registrationBaseAddress(residentDto.getRegistrationBaseAddress())
        .deathDate(residentDto.getDeathDate())
        .deathPlaceAddress(residentDto.getDeathPlaceAddress())
        .email(residentDto.getEmail())
        .loginId(residentDto.getEmail())
        .password(residentDto.getPassword())
        .build();


    FamilyRelationship familyRelationship = FamilyRelationship.builder()
        .pk(pk)
        .resident(resident)
        .familyRelationshipCode(familyRelationshipModifyRequest.getRelationShip())
        .build();

    familyRelationshipRepository.save(familyRelationship);
  }

  @Override
  public void delete(Integer serialNumber, Integer familySerialNumber) {

    FamilyRelationship.Pk pk = new Pk(familySerialNumber, serialNumber);

    FamilyRelationshipDto familyRelationshipDto = familyRelationshipRepository.findByPk(pk);

    //삭제하려는 dto가 존재하지 않는다면
    if(familyRelationshipDto == null){
      throw new FamilyRelationshipNotFoundException();
    }

    Resident resident = familyRelationshipDto.getResident(); //이것도 dto로 받아야 하나?

    FamilyRelationship familyRelationship = FamilyRelationship.builder()
        .pk(pk)
        .resident(resident)
        .familyRelationshipCode(familyRelationshipDto.getFamilyRelationshipCode())
        .build();

    familyRelationshipRepository.delete(familyRelationship);
  }

  @Override
  public List<FamilyRelationshipDto> findBySerialNumber(Integer baseResidentSerialNumber) {
    return familyRelationshipRepository.findAllByPk_FamilyResidentSerialNumber(baseResidentSerialNumber);
  }

  @Override
  public Resident getFather(Integer residentSerialNumber) {
    return familyRelationshipRepository.findFatherByResidentSerialNumber(residentSerialNumber);
  }
}
