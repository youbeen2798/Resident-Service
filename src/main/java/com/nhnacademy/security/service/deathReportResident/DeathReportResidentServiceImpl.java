package com.nhnacademy.security.service.deathReportResident;


import com.nhnacademy.security.domain.deathReport.DeathReportResidentModifyRequest;
import com.nhnacademy.security.domain.deathReport.DeathReportResidentRegisterRequest;
import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.entity.BirthDeathReportResident;
import com.nhnacademy.security.entity.BirthDeathReportResident.Pk;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.BirthDeathReportResidentNotFoundException;
import com.nhnacademy.security.exception.DeathReportAlreadyExistException;
import com.nhnacademy.security.exception.NotMatchRequestException;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.nhnacademy.security.repository.deathReportResident.DeathReportResidentRepository;
import com.nhnacademy.security.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;

@Service("DeathReportService")
public class DeathReportResidentServiceImpl implements DeathReportResidentService{

  private final DeathReportResidentRepository deathReportResidentRepository;
  private final ResidentRepository residentRepository;


  public DeathReportResidentServiceImpl(DeathReportResidentRepository deathReportResidentRepository,
      ResidentRepository residentRepository) {
    this.deathReportResidentRepository = deathReportResidentRepository;
    this.residentRepository = residentRepository;
  }

  @Override
  public void create(Integer serialNumber,
      DeathReportResidentRegisterRequest deathReportResidentRegisterRequest) {

    //사망이 아닌 출생사망유형코드가 들어왔다면
    if(!deathReportResidentRegisterRequest.getBirthDeathTypeCode().equals("사망")){
      throw new NotMatchRequestException();
    }

    BirthDeathReportResident.Pk pk = new Pk(deathReportResidentRegisterRequest.getBirthDeathTypeCode(),
        deathReportResidentRegisterRequest.getDeadResidentSerialNumber());

    //만약 해당 주민에 대한 사망신고가 이미 존재한다면
    if(deathReportResidentRepository.existsByPk(pk)){
      throw new DeathReportAlreadyExistException();
    }

    //사망신고한 주민(행위자)
    ResidentDto reportResidentDto = residentRepository.findByReidentSerialNumber(serialNumber);
    //사망신고한 주민(행위자)
    Resident reportResident = residentDtoToResident(serialNumber, reportResidentDto);

    //사망한 주민
    ResidentDto deadResidentDto = residentRepository.findByReidentSerialNumber(deathReportResidentRegisterRequest.
        getDeadResidentSerialNumber());
    Resident deadResident = residentDtoToResident(deathReportResidentRegisterRequest.getDeadResidentSerialNumber(),
        deadResidentDto);

    if(deadResident == null || reportResident == null){
      throw new ResidentNotFoundException();
    }

    BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
        .pk(pk)
        .resident(deadResident)
        .reportResident(reportResident)
        .birthDeathReportDate(deathReportResidentRegisterRequest.getBirthDeathReportDate())
        .deathReportQualificationsCode(deathReportResidentRegisterRequest.getDeathReportQualificationsCode())
        .emailAddress(deathReportResidentRegisterRequest.getEmailAddress())
        .phoneNumber(deathReportResidentRegisterRequest.getPhoneNumber())
        .build();

    deathReportResidentRepository.save(birthDeathReportResident);
  }

  @Override
  public void modify(Integer serialNumber, Integer targetSerialNumber,
      DeathReportResidentModifyRequest deathReportResidentModifyRequest) {

    BirthDeathReportResident.Pk pk = new Pk("사망", targetSerialNumber);

    //만약 해당 주민에 대한 출생신고가 존재하지 않는데 수정하려 했다면
    if(!deathReportResidentRepository.existsByPk(pk)){
      throw new BirthDeathReportResidentNotFoundException();
    }

    deathReportResidentRepository.updateDeathReportResident(serialNumber, targetSerialNumber,
        deathReportResidentModifyRequest);
  }

  @Override
  public void delete(Integer serialNumber, Integer targetSerialNumber) {

    BirthDeathReportResident.Pk pk = new Pk("사망", targetSerialNumber);

    //만약 해당 주민에 대한 사망신고 기록이 없는데 지우려 한다면
    if(!deathReportResidentRepository.existsByPk(pk)){
      throw new BirthDeathReportResidentNotFoundException();
    }
    deathReportResidentRepository.delete(serialNumber, targetSerialNumber);
  }

  public static Resident residentDtoToResident(Integer serialNumber, ResidentDto residentDto){
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
