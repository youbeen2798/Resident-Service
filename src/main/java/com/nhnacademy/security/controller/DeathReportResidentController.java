package com.nhnacademy.security.controller;

import com.nhnacademy.security.domain.deathReport.DeathReportResidentModifyRequest;
import com.nhnacademy.security.domain.deathReport.DeathReportResidentRegisterRequest;
import com.nhnacademy.security.exception.ValidationFailedException;
import com.nhnacademy.security.service.deathReportResident.DeathReportResidentService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeathReportResidentController {

  private final DeathReportResidentService deathReportResidentService;

  public DeathReportResidentController(DeathReportResidentService deathReportResidentService) {
    this.deathReportResidentService = deathReportResidentService;
  }

  @PostMapping("/residents/{serialNumber}/death")
  public void deathReportResidentRegister(@PathVariable("serialNumber") Integer serialNumber,
      @Valid @RequestBody DeathReportResidentRegisterRequest deathReportResidentRegisterRequest,
      BindingResult bindingResult){

    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }

    deathReportResidentService.create(serialNumber, deathReportResidentRegisterRequest);
  }


  @PutMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
  public void deathReportResidentModify(@PathVariable("serialNumber") Integer serialNumber,
      @PathVariable("targetSerialNumber") Integer targetSerialNumber,
       @RequestBody DeathReportResidentModifyRequest DeathReportResidentModifyRequest){
    deathReportResidentService.modify(serialNumber, targetSerialNumber, DeathReportResidentModifyRequest);
  }

  @DeleteMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
  public void deathReportResidentDelete(@PathVariable("serialNumber") Integer serialNumber,
      @PathVariable("targetSerialNumber") Integer targetSerialNumber){
    deathReportResidentService.delete(serialNumber, targetSerialNumber);
  }
}
