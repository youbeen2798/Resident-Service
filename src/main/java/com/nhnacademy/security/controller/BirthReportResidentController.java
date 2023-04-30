package com.nhnacademy.security.controller;


import com.nhnacademy.security.domain.birthReport.BirthReportResidentModifyRequest;
import com.nhnacademy.security.domain.birthReport.BirthReportResidentRegisterRequest;
import com.nhnacademy.security.exception.ValidationFailedException;
import com.nhnacademy.security.service.birthReportResident.BirthReportResidentService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BirthReportResidentController {

  private final BirthReportResidentService birthReportResidentService;

  public BirthReportResidentController(BirthReportResidentService birthReportResidentService) {
    this.birthReportResidentService = birthReportResidentService;
  }

  @PostMapping("/residents/{serialNumber}/birth")
  public void birthReportResidentRegister(@PathVariable("serialNumber") Integer serialNumber,
      @Valid @RequestBody BirthReportResidentRegisterRequest birthReportResidentRegisterRequest,
      BindingResult bindingResult) {

    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }

    birthReportResidentService.create(serialNumber, birthReportResidentRegisterRequest);
  }

  @PutMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
  public void birthDeathReportResidentModify(@PathVariable("serialNumber") Integer serialNumber,
      @PathVariable("targetSerialNumber") Integer targetSerialNumber, @RequestBody BirthReportResidentModifyRequest birthReportResidentModifyRequest){

    birthReportResidentService.modify(serialNumber, targetSerialNumber,
        birthReportResidentModifyRequest);
  }

  @DeleteMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
  public void birthDeathReportResidentDelete(@PathVariable("serialNumber") Integer serialNumber,
      @PathVariable("targetSerialNumber") Integer targetSerialNumber){

    birthReportResidentService.delete(serialNumber, targetSerialNumber);
  }
}
