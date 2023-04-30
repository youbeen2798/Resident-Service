package com.nhnacademy.security.repository.familyRelationship;

import com.nhnacademy.security.entity.Resident;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {

  Resident findFatherByResidentSerialNumber(Integer residentSerialNumber);

}
