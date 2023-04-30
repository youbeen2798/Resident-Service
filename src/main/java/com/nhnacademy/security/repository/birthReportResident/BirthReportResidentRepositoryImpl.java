package com.nhnacademy.security.repository.birthReportResident;

import com.nhnacademy.security.domain.birthReport.BirthReportResidentModifyRequest;
import com.nhnacademy.security.entity.BirthDeathReportResident;
import com.nhnacademy.security.entity.BirthDeathReportResident.Pk;
import com.nhnacademy.security.entity.QBirthDeathReportResident;
import com.nhnacademy.security.entity.QResident;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

public class BirthReportResidentRepositoryImpl extends QuerydslRepositorySupport implements BirthReportCustom{

  public BirthReportResidentRepositoryImpl() {
    super(BirthDeathReportResident.class);
  }


  @Override
  @Transactional
  public Long updateBirthReportResident(Integer serialNumber,
      Integer targetSerialNumber, BirthReportResidentModifyRequest birthReportResidentModifyRequest) {

    QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
    QResident resident = QResident.resident;

    Resident reportResident = from(resident)
        .where(resident.reidentSerialNumber.eq(serialNumber))
        .select(resident)
        .fetchOne();

    Resident birthResident = from(resident)
        .where(resident.reidentSerialNumber.eq(targetSerialNumber))
        .select(resident)
        .fetchOne();

    if(reportResident == null || birthResident == null){ //출생신고 주민 혹은 출생된 주민 정보가 존재하지 않는다면
      throw new ResidentNotFoundException();
    }

    UpdateClause<JPAUpdateClause> updateBuilder = update(birthDeathReportResident);

    //신고주민 일련번호 업데이트
    updateBuilder.set(birthDeathReportResident.reportResident, reportResident);

    //출생신고일자가 바뀌었다면
    if(!StringUtils.isEmpty(String.valueOf(birthReportResidentModifyRequest.getBirthDeathReportDate()))){
      updateBuilder.set(birthDeathReportResident.birthDeathReportDate, birthReportResidentModifyRequest.getBirthDeathReportDate());
    }
    //출생신고자격코드가 바뀌었다면
    if(!StringUtils.isEmpty(birthReportResidentModifyRequest.getBirthReportQualificationsCode())){
      updateBuilder.set(birthDeathReportResident.birthReportQualificationsCode, birthReportResidentModifyRequest.getBirthReportQualificationsCode());
    }
    //이메일 주소가 바뀌었다면
    if(!StringUtils.isEmpty(birthReportResidentModifyRequest.getEmailAddress())){
      updateBuilder.set(birthDeathReportResident.emailAddress, birthReportResidentModifyRequest.getEmailAddress());
    }
    //전화번호가 바뀌었다면
    if(!StringUtils.isEmpty(birthReportResidentModifyRequest.getPhoneNumber())){
      updateBuilder.set(birthDeathReportResident.phoneNumber, birthReportResidentModifyRequest.getPhoneNumber());
    }

    BirthDeathReportResident.Pk pk = new Pk("출생", targetSerialNumber);

    return updateBuilder
        .where(birthDeathReportResident.pk.eq(pk))
        .execute();
    }

}

//update문
//update(birthDeathReportResident)
//        .set(birthDeathReportResident.birthDeathReportDate, birthReportResidentModifyRequest.getBirthDeathReportDate())
//        .set(birthDeathReportResident.birthReportQualificationsCode, birthReportResidentModifyRequest.getBirthReportQualificationsCode())
//        .set(birthDeathReportResident.emailAddress, birthReportResidentModifyRequest.getEmailAddress())
//        .set(birthDeathReportResident.phoneNumber, birthReportResidentModifyRequest.getPhoneNumber())
//        .where(birthDeathReportResident.pk.residentSerialNumber.eq(targetSerialNumber))
//        .execute();
