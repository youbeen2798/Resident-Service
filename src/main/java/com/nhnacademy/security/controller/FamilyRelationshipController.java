package com.nhnacademy.security.controller;

import com.nhnacademy.security.domain.familyRelationship.FamilyRelationshipModifyRequest;
import com.nhnacademy.security.domain.familyRelationship.FamilyRelationshipRegisterRequest;
import com.nhnacademy.security.exception.ValidationFailedException;
import com.nhnacademy.security.service.familyRelationship.FamilyRelationshipService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyRelationshipController {

  private final FamilyRelationshipService familyRelationshipService;

  public FamilyRelationshipController(FamilyRelationshipService familyRelationshipService) {
    this.familyRelationshipService = familyRelationshipService;
  }

  @PostMapping("/residents/{serialNumber}/relationship")
  public void familyRelationshipRegister(@PathVariable("serialNumber") Integer serialNumber,
      @Valid @RequestBody FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest,
      BindingResult bindingResult){

    //residentSerialNumber : 기준 주민 일련번호
    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }
    familyRelationshipService.create(serialNumber, familyRelationshipRegisterRequest);
  }

  @PutMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
  public void familyRelationshipModify(@PathVariable("serialNumber") Integer serialNumber,
      @PathVariable("familySerialNumber") Integer familySerialNumber,
   @Valid @RequestBody FamilyRelationshipModifyRequest familyRelationshipModifyRequest,
      BindingResult bindingResult){

    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }

    familyRelationshipService.modify(serialNumber, familySerialNumber,
        familyRelationshipModifyRequest);
  }

  @DeleteMapping("residents/{serialNumber}/relationship/{familySerialNumber}")
  public void familyRelationshipDelete(@PathVariable("serialNumber") Integer serialNumber,
      @PathVariable("familySerialNumber") Integer familySerialNumber){
    familyRelationshipService.delete(serialNumber, familySerialNumber);
  }


}

