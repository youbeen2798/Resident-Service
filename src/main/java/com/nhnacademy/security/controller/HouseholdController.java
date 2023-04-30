package com.nhnacademy.security.controller;

import com.nhnacademy.security.domain.household.HouseholdRegisterRequest;
import com.nhnacademy.security.exception.ValidationFailedException;
import com.nhnacademy.security.service.household.HouseholdService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdController {

  private final HouseholdService householdService;

  public HouseholdController(HouseholdService householdService) {
    this.householdService = householdService;
  }

  @PostMapping("/household")
  public void householdRegister(@Valid @RequestBody HouseholdRegisterRequest householdRegisterRequest,
      BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }
    householdService.create(householdRegisterRequest);
  }

  @DeleteMapping("/household/{householdSerialNumber}")
  public void householdDelete(@PathVariable("householdSerialNumber") Integer householdSerialNumber){
    householdService.delete(householdSerialNumber);
  }


}
