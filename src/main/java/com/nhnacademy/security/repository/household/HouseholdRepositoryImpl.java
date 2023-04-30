package com.nhnacademy.security.repository.household;

import com.nhnacademy.security.domain.household.HouseholdRegisterRequest;
import com.nhnacademy.security.entity.Household;
import com.nhnacademy.security.entity.QHousehold;
import com.nhnacademy.security.entity.QResident;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.exception.ResidentNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

public class HouseholdRepositoryImpl extends QuerydslRepositorySupport implements HouseholdRepositoryCustom {

  public HouseholdRepositoryImpl() {
    super(Household.class);
  }

  @Autowired
  JPAQueryFactory queryFactory;

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @Override
  public void create(HouseholdRegisterRequest householdRegisterRequest) {
    QHousehold household = QHousehold.household;
    QResident resident = QResident.resident;

    Resident householdResident = from(resident)
        .where(resident.reidentSerialNumber.eq(householdRegisterRequest.getHouseholdResidentSerialNumber()))
        .select(resident)
        .fetchOne();

    //세대주 주민일련번호가 존재하지 않는다면
    if(householdResident == null){
        throw new ResidentNotFoundException();
    }

    //전체 household 크기
    Integer householdCnt = from(household)
        .select(household)
        .fetch().size() + 1;

    entityManager.createNativeQuery("INSERT INTO household(household_serial_number, household_resident_serial_number,"
        + "household_composition_date, household_composition_reason_code, current_house_movement_address) "
        + "VALUES(?,?,?,?,?)")
        .setParameter(1, householdCnt)
        .setParameter(2, householdRegisterRequest.getHouseholdResidentSerialNumber())
        .setParameter(3,householdRegisterRequest.getHouseholdCompositionDate())
        .setParameter(4, householdRegisterRequest.getHouseholdCompositionReasonCode())
        .setParameter(5, householdRegisterRequest.getCurrentHouseMovementAddress())
        .executeUpdate();


  }

  @Transactional
  @Override
  public void delete(Integer householdSerialNumber) {
    QHousehold household = QHousehold.household;

    queryFactory.delete(household)
        .where(household.householdSerialNumber.eq(householdSerialNumber))
        .execute();
  }

}

