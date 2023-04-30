package com.nhnacademy.security.service.birthReportResident;


import com.nhnacademy.security.domain.birthReport.BirthReportResidentModifyRequest;
import com.nhnacademy.security.domain.birthReport.BirthReportResidentRegisterRequest;
import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.entity.BirthDeathReportResident;
import com.nhnacademy.security.entity.BirthDeathReportResident.Pk;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.BirthDeathReportResidentNotFoundException;
import com.nhnacademy.security.exception.BirthReportAlreadyExistException;
import com.nhnacademy.security.exception.NotMatchRequestException;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.nhnacademy.security.repository.birthReportResident.BirthReportResidentRepository;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;

@Service("BirthReportService")
public class BirthReportResidentServiceImpl implements BirthReportResidentService {

  private final BirthReportResidentRepository birthReportResidentRepository;
  private final ResidentRepository residentRepository;

  public BirthReportResidentServiceImpl(BirthReportResidentRepository birthReportResidentRepository,
      ResidentRepository residentRepository) {
    this.birthReportResidentRepository = birthReportResidentRepository;
    this.residentRepository = residentRepository;
  }


  @Override
  public void create(Integer serialNumber,
      BirthReportResidentRegisterRequest birthReportResidentRegisterRequest) {

    //출생이 아닌 출생사망유형코드가 들어온다면
    if(!birthReportResidentRegisterRequest.getBirthDeathTypeCode().equals("출생")){
      throw new NotMatchRequestException();
    }

    BirthDeathReportResident.Pk pk = new Pk(birthReportResidentRegisterRequest.getBirthDeathTypeCode(),
        birthReportResidentRegisterRequest.getResidentSerialNumber());

    //만약 해당 주민에 대한 출생신고가 이미 존재한다면
    if(birthReportResidentRepository.existsByPk(pk)){
      throw new BirthReportAlreadyExistException();
    }

    //출생신고한 주민(행위자)
    ResidentDto reportResidentDto = residentRepository.findByReidentSerialNumber(serialNumber);
    Resident reportResident = residentDtoToResident(serialNumber, reportResidentDto);

    //태어난 주민
    ResidentDto residentDto = residentRepository.findByReidentSerialNumber(birthReportResidentRegisterRequest.getResidentSerialNumber());
    Resident resident = residentDtoToResident(birthReportResidentRegisterRequest.getResidentSerialNumber(), residentDto);

    if(reportResident == null || resident == null){
      throw new ResidentNotFoundException();
    }

    BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
        .pk(pk)
        .resident(resident)
        .reportResident(reportResident)
        .birthDeathReportDate(birthReportResidentRegisterRequest.getBirthDeathReportDate())
        .birthReportQualificationsCode(birthReportResidentRegisterRequest.getBirthReportQualificationsCode())
        .emailAddress(birthReportResidentRegisterRequest.getEmailAddress())
        .phoneNumber(birthReportResidentRegisterRequest.getPhoneNumber())
        .build();

    birthReportResidentRepository.save(birthDeathReportResident);
  }

  @Override
  public void modify(Integer serialNumber,
      Integer targetSerialNumber,
      BirthReportResidentModifyRequest birthReportResidentModifyRequest) {

    BirthDeathReportResident.Pk pk = new Pk("출생", targetSerialNumber);

    //만약 해당 신고자가 해당 주민을 출생신고를 아직 하지 않았는데 수정하려 한다면
    if(!birthReportResidentRepository.existsByPk(pk)){
      throw new BirthDeathReportResidentNotFoundException();
    }
    birthReportResidentRepository.updateBirthReportResident(serialNumber,
        targetSerialNumber, birthReportResidentModifyRequest);
  }

  @Override
  public void delete(Integer serialNumber, Integer targetSerialNumber) {

    BirthDeathReportResident.Pk pk = new Pk("출생",targetSerialNumber);

    //해당 주민에 대한 출생신고 기록이 없는데 지우려 한다면
    if(!birthReportResidentRepository.existsByPk(pk)){
      throw new BirthDeathReportResidentNotFoundException();
    }
    birthReportResidentRepository.delete(serialNumber, targetSerialNumber, "출생");
  }



  public Resident residentDtoToResident(Integer serialNumber, ResidentDto residentDto){
    return Resident.builder()
        .reidentSerialNumber(serialNumber)
        .name(residentDto.getName())
        .residentRegistrationNumber(residentDto.getResidentRegistrationNumber())
        .birthDate(residentDto.getBirthDate())
        .birthPlaceCode(residentDto.getBirthPlaceCode())
        .registrationBaseAddress(residentDto.getRegistrationBaseAddress())
        .deathDate(residentDto.getDeathDate())
        .deathPlaceCode(residentDto.getDeathPlaceCode())
        .deathPlaceAddress(residentDto.getDeathPlaceAddress())
        .build();
  }
}
