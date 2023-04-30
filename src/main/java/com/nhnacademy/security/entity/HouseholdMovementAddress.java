package com.nhnacademy.security.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {

  @EmbeddedId
  private Pk pk;

  @MapsId("householdSerialNumber")
  @ManyToOne
  @JoinColumn(name = "household_serial_number")
  private Household household;

  @Column(name = "house_movement_address")
  private String houseMovementAddress;

  @Column(name = "last_address_yn")
  private Character lastAddressYn;


  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Getter
  @Setter
  @Embeddable
  public static class Pk implements Serializable {
    //전입신고일자
    @Column(name = "house_movement_report_date")
    private LocalDate houseMovementReportDate;

    //세대일련번호
    @Column(name = "household_serial_number")
    private Integer householdSerialNumber;
  }

}
