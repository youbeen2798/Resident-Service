package com.nhnacademy.security.service.birthReportResident;


import com.nhnacademy.security.domain.birthReport.BirthReportResidentModifyRequest;
import com.nhnacademy.security.domain.birthReport.BirthReportResidentRegisterRequest;

public interface BirthReportResidentService {

  void create(Integer serialNumber, BirthReportResidentRegisterRequest birthReportResidentRegisterRequest);

  void modify(Integer serialNumber, Integer targetSerialNumber, BirthReportResidentModifyRequest birthReportResidentModifyRequest);

  void delete(Integer serialNumber, Integer targetSerialNumber);


}
