package com.nhnacademy.security.controller;


import com.nhnacademy.security.dto.FamilyRelationshipDto;
import com.nhnacademy.security.dto.HouseholdDto;
import com.nhnacademy.security.dto.HouseholdMovementAddressDto;
import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.nhnacademy.security.service.birthReportResident.BirthReportResidentService;
import com.nhnacademy.security.service.familyRelationship.FamilyRelationshipService;
import com.nhnacademy.security.service.household.HouseholdService;
import com.nhnacademy.security.service.householdMovementAddress.HouseholdMovementAddressService;
import com.nhnacademy.security.service.resident.ResidentService;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/resident")
@Controller
public class CertificateController {

  private final ResidentService residentService;
  private final FamilyRelationshipService familyRelationshipService;
  private final HouseholdService householdService;
  private final HouseholdMovementAddressService householdMovementAddressService;
  private final BirthReportResidentService birthReportResidentService;

  public CertificateController(ResidentService residentService,
      FamilyRelationshipService familyRelationshipService, HouseholdService householdService,
      HouseholdMovementAddressService householdMovementAddressService,
      BirthReportResidentService birthReportResidentService) {
    this.residentService = residentService;
    this.familyRelationshipService = familyRelationshipService;
    this.householdService = householdService;
    this.householdMovementAddressService = householdMovementAddressService;
    this.birthReportResidentService = birthReportResidentService;
  }

  @ModelAttribute("resident")
  public Resident getResident(@PathVariable("residentSerialNumber")
  Integer residentSerialNumber){
    ResidentDto residentdto = residentService.getResident(residentSerialNumber);

    Resident resident = Resident.builder()
        .reidentSerialNumber(residentdto.getReidentSerialNumber())
        .name(residentdto.getName())
        .residentRegistrationNumber(residentdto.getResidentRegistrationNumber())
        .genderCode(residentdto.getGenderCode())
        .birthDate(residentdto.getBirthDate())
        .birthPlaceCode(residentdto.getBirthPlaceCode())
        .registrationBaseAddress(residentdto.getRegistrationBaseAddress())
        .deathDate(residentdto.getDeathDate())
        .deathPlaceCode(residentdto.getDeathPlaceCode())
        .deathPlaceAddress(residentdto.getDeathPlaceAddress())
        .email(residentdto.getEmail())
        .loginId(residentdto.getLoginId())
        .password(residentdto.getPassword())
        .build();

    if(Objects.isNull(resident)){
      throw new ResidentNotFoundException();
    }
    return resident;
  }


  @GetMapping("/{residentSerialNumber}/familyRelationsCertificate")
  public String familyRelationsCertificateView(@PathVariable("residentSerialNumber") Integer residentSerialNumber, Model model){

    List<FamilyRelationshipDto> familyRelationshipList = familyRelationshipService.findBySerialNumber(residentSerialNumber);
    model.addAttribute("familyRelationshipList", familyRelationshipList);

    return "familyRelationsCertificateView";
  }

  @GetMapping("/{residentSerialNumber}/residentRegistrationCopy")
  public String idCardView(@PathVariable("residentSerialNumber")
  Integer residentSerialNumber, Model model){

    List<HouseholdDto> householdDtoList = householdService.getAllByHouseholdResidentSerialNumber(residentSerialNumber);
    model.addAttribute("householdDtoList", householdDtoList);

    List<HouseholdMovementAddressDto> householdMovementAddressDtoList =
        householdMovementAddressService.getAllByHouseholdResidentSerialNumber(residentSerialNumber);

    model.addAttribute("householdMovementAddressDtoList", householdMovementAddressDtoList);

    List<FamilyRelationshipDto> familyRelationshipList = familyRelationshipService.findBySerialNumber(residentSerialNumber);
    model.addAttribute("familyRelationshipList", familyRelationshipList);
    return "idCardView";
  }

  @GetMapping("/{residentSerialNumber}/birthCertificate")
  public String birthCertificate(@PathVariable("residentSerialNumber")
  Integer residentSerialNumber, Model model){

    List<FamilyRelationshipDto> familyRelationshipList = familyRelationshipService.findBySerialNumber(residentSerialNumber);

    model.addAttribute("familyRelationshipList", familyRelationshipList);

    return "birthCertificateView";
  }

  @GetMapping("/{residentSerialNumber}/deathCertificate")
  public String familyRelationsCertificate(@PathVariable("residentSerialNumber")
  Integer residentSerialNumber, Model model){

    List<FamilyRelationshipDto> familyRelationshipList = familyRelationshipService.findBySerialNumber(residentSerialNumber);

    model.addAttribute("familyRelationshipList", familyRelationshipList);

    return "deathCertificateView";

  }

}
