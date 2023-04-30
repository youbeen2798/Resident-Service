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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
//분홍이 비식별 초록이 식별 노랑은 PK

  @EmbeddedId
  private Pk pk;

  @Column(name = "report_date")
  private LocalDate report_date;

  @Column(name = "household_relationship_code")
  private String household_relationship_code;

  @Column(name = "household_composition_change_reason_code")
  private String household_composition_change_reason_code;

  @MapsId("householdSerialNumber")
  @ManyToOne
  @JoinColumn(name = "household_serial_number")
  private Household household;

  @MapsId("residentSerialNumber")
  @ManyToOne
  @JoinColumn(name = "resident_serial_number")
  private Resident resident;

  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Getter
  @Setter
  @Embeddable
  public static class Pk implements Serializable {
    @Column(name = "household_serial_number")
    private Integer householdSerialNumber;

    @Column(name = "resident_serial_number")
    private Integer residentSerialNumber;
  }
}
