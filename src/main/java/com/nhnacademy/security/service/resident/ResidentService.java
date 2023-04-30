package com.nhnacademy.security.service.resident;

import com.nhnacademy.security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.security.domain.resident.ResidentRegisterRequest;
import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.dto.ResidentResponse;
import com.nhnacademy.security.entity.Resident;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {

  void create(ResidentRegisterRequest residentRegisterRequest);

  void modify(Integer serialNumber,
      ResidentModifyRequest residentModifyRequest);

  List<ResidentDto> getAllResidents();
  List<ResidentDto> getResidents(Pageable pageable);

  Page<Resident> getPagedResidents(Pageable pageable);

  ResidentDto getResident(Integer residentSerialNumber);


  ResidentResponse getResidentByEmail(String email);

  void delete(Integer residentSerialNumber);

  ResidentDto getResident(String loginId);

}
