package com.nhnacademy.security.domain.household;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdRegisterRequest {

  //세대주주민일련번호
  @NotNull
  private Integer householdResidentSerialNumber;

  //세대구성일자
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate householdCompositionDate;

  //세대구성사유코드
  @NotEmpty
  private String householdCompositionReasonCode;

  //현재전입주소
  @NotEmpty
  private String currentHouseMovementAddress;

}
