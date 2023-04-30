package com.nhnacademy.security.repository.resident;

import com.nhnacademy.security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.security.dto.ResidentResponse;
import com.nhnacademy.security.entity.QAuthority;
import com.nhnacademy.security.entity.QResident;
import com.nhnacademy.security.entity.Resident;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;


public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {

  public ResidentRepositoryImpl() {
    super(Resident.class);
  }

  @Autowired
  private JPAQueryFactory queryFactory;

  @Transactional
  @Override
  public void modify(Integer serialNumber, ResidentModifyRequest residentModifyRequest){
    QResident resident = QResident.resident;

    queryFactory
        .update(resident)
        .set(resident.name, residentModifyRequest.getName())
        .where(resident.reidentSerialNumber.eq(serialNumber))
        .execute();

  }

  public ResidentResponse retrieveResident(String email) {
    QResident resident = QResident.resident;
    QAuthority authority = QAuthority.authority1;

    ResidentResponse residentResponse = from(resident)
        .select(Projections.constructor(ResidentResponse.class,
            resident.reidentSerialNumber,
            resident.loginId,
            resident.password,
            resident.email,
            authority.authority
        ))
        .innerJoin(resident.authority, authority)
        .where(resident.email.eq(email))
        .fetchFirst();

    return residentResponse;
  }


}
