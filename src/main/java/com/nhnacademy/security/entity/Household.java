package com.nhnacademy.security.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Entity
@Table(name = "household")
public class Household {

  @Id
  @Column(name = "household_serial_number")
  private Integer householdSerialNumber;

  @ManyToOne
  @JoinColumn(name = "household_resident_serial_number",
      referencedColumnName = "resident_serial_number")
  private Resident resident;


  @Column(name = "household_composition_date")
  private LocalDate householdCompositionDate;

  @Column(name = "household_composition_reason_code")
  private String householdCompositionReasonCode;

  @Column(name = "current_house_movement_address")
  private String currentHouseMovementAddress;
}
