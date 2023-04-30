package com.nhnacademy.security.repository.birthReportResident;

import com.nhnacademy.security.entity.BirthDeathReportResident;
import com.nhnacademy.security.entity.BirthDeathReportResident.Pk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BirthReportResidentRepository extends JpaRepository<BirthDeathReportResident, Pk>,
    BirthReportCustom {

  @Transactional
  @Modifying
  @Query("delete from BirthDeathReportResident b where b.pk.residentSerialNumber = :targetSerialNumber and b.reportResident.reidentSerialNumber = :serialNumber and b.pk.birthDeathTypeCode =:birthDeathTypeCode")
  void delete(@Param("serialNumber")Integer serialNumber, @Param("targetSerialNumber") Integer targetSerialNumber, @Param("birthDeathTypeCode") String birthDeathTypeCode);

  boolean existsByPk(BirthDeathReportResident.Pk pk);


}
