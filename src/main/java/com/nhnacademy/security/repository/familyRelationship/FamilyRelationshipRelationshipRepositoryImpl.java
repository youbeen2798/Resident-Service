package com.nhnacademy.security.repository.familyRelationship;

import com.nhnacademy.security.entity.FamilyRelationship;
import com.nhnacademy.security.entity.QFamilyRelationship;
import com.nhnacademy.security.entity.QResident;
import com.nhnacademy.security.entity.Resident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FamilyRelationshipRelationshipRepositoryImpl extends QuerydslRepositorySupport implements
    FamilyRelationshipRepositoryCustom {

  public FamilyRelationshipRelationshipRepositoryImpl() {
    super(FamilyRelationship.class);
  }

  @Override
  public Resident findFatherByResidentSerialNumber(Integer residentSerialNumber) {
    QResident resident = QResident.resident;
    QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

    return from(familyRelationship)
        .innerJoin(familyRelationship.resident, resident)
        .where(familyRelationship.resident.reidentSerialNumber.eq(residentSerialNumber))
        .where(familyRelationship.familyRelationshipCode.eq("부"))
        .select(resident)
        .fetchOne();
    }
}
