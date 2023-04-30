package com.nhnacademy.security.domain.householdMovementAddress;

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
public class HouseholdMovementAddressRegisterRequest {

  //전입신고일자
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  public LocalDate houseMovementReportDate;

  //전입주소
  @NotEmpty
  public String houseMovementAddress;

  //최종주소여부
  @NotNull
  public Character lastAddressYn;

}
