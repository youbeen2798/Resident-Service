package com.nhnacademy.security.service.familyRelationship;

import com.nhnacademy.security.domain.familyRelationship.FamilyRelationshipModifyRequest;
import com.nhnacademy.security.domain.familyRelationship.FamilyRelationshipRegisterRequest;
import com.nhnacademy.security.dto.FamilyRelationshipDto;
import com.nhnacademy.security.entity.Resident;
import java.util.List;

public interface FamilyRelationshipService {
  void create(Integer serialNumber, FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest);

  void modify(Integer serialNumber, Integer familySerialNumber,
      FamilyRelationshipModifyRequest familyRelationshipModifyRequest);

  void delete(Integer serialNumber, Integer familySerialNumber);

  List<FamilyRelationshipDto> findBySerialNumber(Integer baseResidentSerialNumber);

  Resident getFather(Integer residentSerialNumber);
}
