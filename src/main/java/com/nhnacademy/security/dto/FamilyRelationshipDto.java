package com.nhnacademy.security.dto;

import com.nhnacademy.security.entity.FamilyRelationship;
import com.nhnacademy.security.entity.Resident;

public interface FamilyRelationshipDto {

  FamilyRelationship.Pk getPk();

  Resident getResident();

  String getFamilyRelationshipCode();
}
