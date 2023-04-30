package com.nhnacademy.security.repository.householdMovementAddress;


import com.nhnacademy.security.domain.householdMovementAddress.HouseholdMovementAddressModifyRequest;
import com.nhnacademy.security.entity.HouseholdMovementAddress;
import com.nhnacademy.security.entity.HouseholdMovementAddress.Pk;
import com.nhnacademy.security.entity.QHouseholdMovementAddress;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

public class HouseholdMovementAddressRepositoryImpl extends QuerydslRepositorySupport implements HouseholdMovementAddressRepositoryCustom {

  public HouseholdMovementAddressRepositoryImpl() {
    super(HouseholdMovementAddress.class);
  }


  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  JPAQueryFactory queryFactory;

  @Transactional
  @Override
  public void updateLastAddress(Integer householdSerialNumber) {
    QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;

    JPAUpdateClause update = new JPAUpdateClause(entityManager, householdMovementAddress);

    update
        .set(householdMovementAddress.lastAddressYn, 'N')
        .where(householdMovementAddress.lastAddressYn.eq('Y'))
        .where(householdMovementAddress.household.householdSerialNumber.eq(householdSerialNumber))
        .execute();
  }

  @Transactional
  @Override
  public Long modify(HouseholdMovementAddress.Pk pk,
      HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest) {

    QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;

    UpdateClause<JPAUpdateClause> updateBuilder = update(householdMovementAddress);

    //전입주소가 바뀌었다면
    if(!StringUtils.isEmpty(householdMovementAddressModifyRequest.getHouseMovementAddress())){
      updateBuilder.set(householdMovementAddress.houseMovementAddress, householdMovementAddressModifyRequest.getHouseMovementAddress());
    }
    //최종주소여부가 바뀌었다면
    if(!StringUtils.isEmpty(String.valueOf(householdMovementAddressModifyRequest.getLastAddressYn()))){
      updateBuilder.set(householdMovementAddress.lastAddressYn, householdMovementAddressModifyRequest.getLastAddressYn());
    }

    return updateBuilder
        .where(householdMovementAddress.pk.eq(pk))
        .execute();
  }

  @Transactional
  @Override
  public void delete(Pk pk) {

    QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;

    queryFactory.delete(householdMovementAddress)
        .where(householdMovementAddress.pk.eq(pk))
        .execute();
  }
}
