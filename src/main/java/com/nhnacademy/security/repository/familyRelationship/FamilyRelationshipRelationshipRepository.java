package com.nhnacademy.security.repository.familyRelationship;

import com.nhnacademy.security.dto.FamilyRelationshipDto;
import com.nhnacademy.security.entity.FamilyRelationship;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>,
    FamilyRelationshipRepositoryCustom {

  FamilyRelationshipDto findByPk(FamilyRelationship.Pk pk);

  List<FamilyRelationshipDto> findAllByPkResidentSerialNumber(Integer residentSerialNumber);

  List<FamilyRelationshipDto> findAllByPk_FamilyResidentSerialNumber(Integer familyResidentSerialNumber);
}
