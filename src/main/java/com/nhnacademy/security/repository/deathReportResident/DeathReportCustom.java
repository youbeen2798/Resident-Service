package com.nhnacademy.security.repository.deathReportResident;

import com.nhnacademy.security.domain.deathReport.DeathReportResidentModifyRequest;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DeathReportCustom {

  Long updateDeathReportResident(Integer serialNumber, Integer targetSerialNumber,
  DeathReportResidentModifyRequest deathReportResidentModifyRequest);

}
