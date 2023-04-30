package com.nhnacademy.security.repository.birthReportResident;

import com.nhnacademy.security.domain.birthReport.BirthReportResidentModifyRequest;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthReportCustom {

  Long updateBirthReportResident(Integer serialNumber,
      Integer targetSerialNumber, BirthReportResidentModifyRequest birthReportResidentModifyRequest);

}
