package com.nhnacademy.security.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {

  @EmbeddedId
  private Pk pk;

  @MapsId("residentSerialNumber")
  @ManyToOne
  @JoinColumn(referencedColumnName = "resident_serial_number", name = "base_resident_serial_number")
  private Resident resident;

  @Column(name = "family_relationship_code")
  private String familyRelationshipCode; //가족 관계코드


  @NoArgsConstructor
  @EqualsAndHashCode
  @Embeddable
  @AllArgsConstructor
  public static class Pk implements Serializable {
    @Column(name = "family_resident_serial_number")
    private Integer familyResidentSerialNumber; //가족 주민 일련번호

    @Column(name = "base_resident_serial_number")
    private Integer residentSerialNumber; //기준 주민 일련번호
  }

}
