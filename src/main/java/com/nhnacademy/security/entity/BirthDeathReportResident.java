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

@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

  @EmbeddedId
  private Pk pk;

  @ManyToOne
  @JoinColumn(
       name = "report_resident_serial_number"
      ,referencedColumnName = "resident_serial_number")
  private Resident reportResident; //신고주민일련번호

  @MapsId("residentSerialNumber")
  @ManyToOne
  @JoinColumn(name = "resident_serial_number",
  referencedColumnName = "resident_serial_number")
  private Resident resident; //주민일련번호

  @Column(name = "birth_death_report_date")
  private LocalDate birthDeathReportDate; //출생사망신고일자

  @Column(name = "birth_report_qualifications_code")
  private String birthReportQualificationsCode; //출생신고자격코드

  @Column(name = "death_report_qualifications_code")
  private String deathReportQualificationsCode; //사망신고자격코드

  @Column(name = "email_address")
  private String emailAddress; //이메일주소

  @Column(name = "phone_number")
  private String phoneNumber; //전화번호

  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Getter
  @Setter
  @Embeddable
  public static class Pk implements Serializable {
    @Column(name = "birth_death_type_code")
    private String birthDeathTypeCode; //출생사망유형코드

    @Column(name = "resident_serial_number")
    private Integer residentSerialNumber; //주민일련번호

  }
}
//  출생사망신고주민 테이블
//  PK: resident_serial_number,
//  birth_death_type_code
//  FK: report_resident_serial_number
