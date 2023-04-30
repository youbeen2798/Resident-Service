package com.nhnacademy.security.repository.resident;

import com.nhnacademy.security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.security.dto.ResidentResponse;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ResidentRepositoryCustom {

  void modify(Integer serialNumber, ResidentModifyRequest residentModifyRequest);

  ResidentResponse retrieveResident(String email);

}
