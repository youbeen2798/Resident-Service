package com.nhnacademy.security.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Builder
@Getter
@AllArgsConstructor
@Entity
@Table(name = "resident")
public class Resident {

  @Id
  @Column(name = "resident_serial_number")
  private Integer reidentSerialNumber; //주민일련번호

  @Column(name = "name")
  private String name; //성명

  @Column(name = "resident_registration_number")
  private String residentRegistrationNumber; //주민등록번호

  @Column(name = "gender_code")
  private String genderCode; //성별코드

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column(name = "birth_date")
  private LocalDateTime birthDate; //출생일시

  @Column(name = "birth_place_code")
  private String birthPlaceCode; //출생장소코드

  @Column(name = "registration_base_address")
  private String registrationBaseAddress; //등록기준지주소

  @Column(name = "death_date")
  private LocalDateTime deathDate; //사망일시

  @Column(name = "death_place_code")
  private String deathPlaceCode; //사망장소코드

  @Column(name = "death_place_address")
  private String deathPlaceAddress; //사망장소주소

  @Column(name = "id")
  private String loginId;

  @Column(name = "password")
  public String password;

  @Column(name = "email")
  public String email;

//  @JoinColumn(name = "authority", referencedColumnName = "authority")
  @OneToOne(mappedBy = "resident", fetch = FetchType.EAGER)
  private Authority authority;



}
