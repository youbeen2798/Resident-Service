package com.nhnacademy.security.domain.deathReport;

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
public class DeathReportResidentRegisterRequest {

  //출생사망유형코드
  @NotEmpty
  private String birthDeathTypeCode;

  //사망한 주민일련번호
  @NotNull
  private Integer deadResidentSerialNumber;

  //출생사망신고일자
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDeathReportDate;

  //사망신고자격코드
  private String deathReportQualificationsCode;

  //이메일 주소
  private String emailAddress;

  //전화번호
  private String phoneNumber;

}
