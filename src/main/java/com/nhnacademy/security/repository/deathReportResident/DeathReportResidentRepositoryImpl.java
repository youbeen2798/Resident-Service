package com.nhnacademy.security.repository.deathReportResident;


import com.nhnacademy.security.domain.deathReport.DeathReportResidentModifyRequest;
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

public class DeathReportResidentRepositoryImpl extends QuerydslRepositorySupport implements
    DeathReportCustom {

  public DeathReportResidentRepositoryImpl() {
    super(BirthDeathReportResident.class);
  }

  @Transactional
  @Override
  public Long updateDeathReportResident(Integer serialNumber,
      Integer targetSerialNumber,
      DeathReportResidentModifyRequest deathReportResidentModifyRequest) {
    //targetSerialNumber : 사망한 사람
    //serialNumber : 사망신고한 사람

    QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
    QResident resident = QResident.resident;

    Resident reportResident = from(resident)
        .where(resident.reidentSerialNumber.eq(serialNumber))
        .select(resident)
        .fetchOne();

    Resident deadResident = from(resident)
        .where(resident.reidentSerialNumber.eq(targetSerialNumber))
        .select(resident)
        .fetchOne();

    if(reportResident == null || deadResident == null){ //사망신고 주민 혹은 사망한 주민 정보가 존재하지 않는다면
      throw new ResidentNotFoundException();
    }

    UpdateClause<JPAUpdateClause> updateBuilder = update(birthDeathReportResident);

    //신고주민 일련번호 업데이트
    updateBuilder.set(birthDeathReportResident.reportResident, reportResident);

    //사망신고일자가 바뀌었다면
    if(!StringUtils.isEmpty(String.valueOf(deathReportResidentModifyRequest.getBirthDeathReportDate()))){
      updateBuilder.set(birthDeathReportResident.birthDeathReportDate, deathReportResidentModifyRequest.getBirthDeathReportDate());
    }
    //사망신고자격코드가 바뀌었다면
    if(!StringUtils.isEmpty(deathReportResidentModifyRequest.getDeathReportQualificationsCode())){ //사망신고 자격코드가 바뀌었다면
      updateBuilder.set(birthDeathReportResident.deathReportQualificationsCode, deathReportResidentModifyRequest.getDeathReportQualificationsCode());
    }
    //이메일 주소가 바뀌었다면
    if(!StringUtils.isEmpty(deathReportResidentModifyRequest.getEmailAddress())){ //이메일 주소가 바뀌었다면
      updateBuilder.set(birthDeathReportResident.emailAddress, deathReportResidentModifyRequest.getEmailAddress());
    }
    //전화번호가 바뀌었다면
    if(!StringUtils.isEmpty(deathReportResidentModifyRequest.getPhoneNumber())){ //휴대전화번호가 바뀌었다면
        updateBuilder.set(birthDeathReportResident.phoneNumber, deathReportResidentModifyRequest.getPhoneNumber());
    }

    BirthDeathReportResident.Pk pk = new Pk("사망", targetSerialNumber);

    return updateBuilder
        .where(birthDeathReportResident.pk.eq(pk))
        .execute();
  }

}

