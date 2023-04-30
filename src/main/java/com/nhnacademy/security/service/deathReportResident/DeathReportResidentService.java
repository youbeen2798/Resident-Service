package com.nhnacademy.security.service.deathReportResident;


import com.nhnacademy.security.domain.deathReport.DeathReportResidentModifyRequest;
import com.nhnacademy.security.domain.deathReport.DeathReportResidentRegisterRequest;

public interface DeathReportResidentService {

  void create(Integer serialNumber, DeathReportResidentRegisterRequest deathReportResidentRegisterRequest);

  void modify(Integer serialNumber, Integer targetSerialNumber,
      DeathReportResidentModifyRequest deathReportResidentModifyRequest);

  void delete(Integer serialNumber, Integer targetSerialNumber);

}
