package com.nhnacademy.security.controller;

import static java.time.LocalDate.parse;

import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressModifyRequest;
import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressRegisterRequest;
import com.nhnacademy.security.exception.ValidationFailedException;
import com.nhnacademy.security.service.householdMovementAddress.HouseholdMovementAddressService;
import java.time.format.DateTimeFormatter;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdMovementAddressController {

  private final HouseholdMovementAddressService householdMovementAddressService;


  public HouseholdMovementAddressController(HouseholdMovementAddressService householdMovementAddressService) {
    this.householdMovementAddressService = householdMovementAddressService;
  }

  @PostMapping("/household/{householdSerialNumber}/movement")
  public void householdMovementAddressRegister(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
      @Valid @RequestBody HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest,
      BindingResult bindingResult){

    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }
    householdMovementAddressService.create(householdSerialNumber,
        householdMovementAddressRegisterRequest);
  }

  @PutMapping("/household/{householdSerialNumber}/movement/{reportDate}")
  public void householdMovementAddressModify(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
       @PathVariable("reportDate") String reportDate, @RequestBody HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest){

    householdMovementAddressService.modify(householdSerialNumber, parse(reportDate, DateTimeFormatter.ISO_DATE),
        householdMovementAddressModifyRequest);
  }

  @DeleteMapping("/household/{householdSerialNumber}/movement/{reportDate}")
  public void householdMovementAddressDelete(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
      @PathVariable("reportDate") String reportDate) {

    householdMovementAddressService.delete(householdSerialNumber, parse(reportDate, DateTimeFormatter.ISO_DATE));
  }
}
