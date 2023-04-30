package com.nhnacademy.security.domain.birthReport;

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
public class BirthReportResidentRegisterRequest {

  // 주민 일련번호
  @NotNull
  private Integer residentSerialNumber;

  //출생사망유형코드
  @NotEmpty
  private String birthDeathTypeCode;

  //출생사망신고일자
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDeathReportDate;

  //출생신고자격코드
  private String birthReportQualificationsCode;

  //이메일 주소
  private String emailAddress;

  //전화번호
  @NotNull
  private String phoneNumber;
}
