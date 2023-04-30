package com.nhnacademy.security.controller;

import com.nhnacademy.security.domain.resident.ResidentInformationRequest;
import com.nhnacademy.security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.security.domain.resident.ResidentRegisterRequest;
import com.nhnacademy.security.dto.FamilyRelationshipDto;
import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.exception.ValidationFailedException;
import com.nhnacademy.security.service.familyRelationship.FamilyRelationshipService;
import com.nhnacademy.security.service.resident.ResidentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ResidentController {

  private final ResidentService residentService;
  private final FamilyRelationshipService familyRelationshipService;

  public ResidentController(ResidentService residentService,
      FamilyRelationshipService familyRelationshipService) {
    this.residentService = residentService;
    this.familyRelationshipService = familyRelationshipService;
  }

  @PostMapping("/residents")
  public void residentRegister(@Valid @RequestBody ResidentRegisterRequest residentRegisterRequest, BindingResult bindingResult){

    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }
    residentService.create(residentRegisterRequest);
  }

  @PutMapping("/residents/{serialNumber}")
  public void residentModify(@PathVariable("serialNumber") Integer serialNumber,
      @Valid @RequestBody ResidentModifyRequest residentModifyRequest,
      BindingResult bindingResult){

    if(bindingResult.hasErrors()){
      throw new ValidationFailedException(bindingResult);
    }
    residentService.modify(serialNumber, residentModifyRequest);
  }


  @GetMapping("/resident/{residentSerialNumber}/delete")
  public String residentDelete(@PathVariable("residentSerialNumber") Integer serialNumber){
    residentService.delete(serialNumber);

    return "redirect:/residents";
  }


  @GetMapping("/residents")
  public ModelAndView getAllResidents(@PageableDefault(size = 5, sort = "reidentSerialNumber",
      direction = Sort.Direction.ASC) Pageable pageable,
      @RequestParam(name = "page", defaultValue = "0") int page){

    List<ResidentDto> allResidents = residentService.getAllResidents();
    List<ResidentDto> residentDtoList = residentService.getResidents(pageable);
    ModelAndView modelAndView = new ModelAndView("residentViewPage");

    //마지막 페이지 여부 확인
    boolean lastPage = page >= (allResidents.size() / (double) 5) - 1;

    //이전 페이지
    modelAndView.addObject("residentDtoList", residentDtoList);
    modelAndView.addObject("beforepage", page - 1);
    modelAndView.addObject("page", page);
    modelAndView.addObject("nextpage", page + 1);
    modelAndView.addObject("lastpage", lastPage);

    return modelAndView;
  }

  @GetMapping("/residents/{residentSerialNumber}")
  public ModelAndView getOneResident(@PathVariable("residentSerialNumber") Integer residentSerialNumber){

    //특정 주민
    ResidentDto residentDto = residentService.getResident(residentSerialNumber);
    List<FamilyRelationshipDto> familyRelationshipDtoList = familyRelationshipService.findBySerialNumber(residentSerialNumber);

    ModelAndView modelAndView = new ModelAndView("oneResidentViewPage");

    modelAndView.addObject("resident", residentDto);

    modelAndView.addObject("familyRelationshipList", familyRelationshipDtoList);

    return modelAndView;
  }

  @GetMapping("/residents/paging")
  public ModelAndView getPagedResidents(@PageableDefault(size = 5, sort = "reidentSerialNumber", direction = Sort.Direction.ASC) Pageable pageable,
      Model model){

    model.addAttribute("posts", residentService.getPagedResidents(pageable));

    ModelAndView modelAndView = new ModelAndView("residentViewPage");
    return modelAndView;
  }

}
