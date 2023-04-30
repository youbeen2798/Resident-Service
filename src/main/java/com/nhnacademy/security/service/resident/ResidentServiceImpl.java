package com.nhnacademy.security.service.resident;

import com.nhnacademy.security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.security.domain.resident.ResidentRegisterRequest;
import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.dto.ResidentResponse;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {

  private final ResidentRepository residentRepository;

  public ResidentServiceImpl(ResidentRepository residentRepository) {
    this.residentRepository = residentRepository;
  }

  @Transactional
  @Override
  public void create(ResidentRegisterRequest residentRegisterRequest) {

    Integer residentSerialNumber = residentRepository.findAll().size() + 1;

    Resident resident = Resident.builder()
        .reidentSerialNumber(residentSerialNumber)
        .name(residentRegisterRequest.getName())
        .residentRegistrationNumber(residentRegisterRequest.getResidentRegistrationNumber())
        .genderCode(residentRegisterRequest.getGenderCode())
        .birthDate(residentRegisterRequest.getBirthDate())
        .birthPlaceCode(residentRegisterRequest.getBirthPlaceCode())
        .registrationBaseAddress(residentRegisterRequest.getRegistrationBaseAddress())
        .build();

    residentRepository.save(resident);
  }

  @Transactional
  @Override
  public void modify(Integer serialNumber,
      ResidentModifyRequest residentModifyRequest) {

    residentRepository.modify(serialNumber, residentModifyRequest);
  }

  @Override
  public List<ResidentDto> getAllResidents() {
    return residentRepository.findAllBy();
  }

  public List<ResidentDto> getResidents(Pageable pageable) {
    return residentRepository.getAllBy(pageable).getContent();
  }

  @Override
  public Page<Resident> getPagedResidents(Pageable pageable) {
    return residentRepository.findAll(pageable);
  }

  public ResidentDto getResident(Integer residentSerialNumber) {
    return residentRepository.findByReidentSerialNumber(residentSerialNumber);
  }

  @Override
  public ResidentResponse getResidentByEmail(String email) {
    return residentRepository.retrieveResident(email);
  }

  @Override
  public void delete(Integer residentSerialNumber) {
    residentRepository.deleteById(residentSerialNumber);
  }

  @Override
  public ResidentDto getResident(String loginId) {
    return residentRepository.findByLoginId(loginId).orElseThrow(ResidentNotFoundException::new);
  }


}
