package com.nhnacademy.security.repository.deathReportResident;

import com.nhnacademy.security.entity.BirthDeathReportResident;
import com.nhnacademy.security.entity.BirthDeathReportResident.Pk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, Pk>,
    DeathReportCustom {

  @Modifying
  @Transactional
  @Query("delete from BirthDeathReportResident b where b.pk.residentSerialNumber = :targetSerialNumber and b.reportResident.reidentSerialNumber = :serialNumber")
  void delete(@Param("serialNumber") Integer serialNumber, @Param("targetSerialNumber") Integer targetSerialNumber);

  boolean existsByPk(BirthDeathReportResident.Pk pk);
}
