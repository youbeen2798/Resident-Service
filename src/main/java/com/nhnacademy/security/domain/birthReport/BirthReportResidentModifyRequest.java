package com.nhnacademy.security.domain.birthReport;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BirthReportResidentModifyRequest {

  //출생사망신고일자
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDeathReportDate;

  //출생신고자격코드
  private String birthReportQualificationsCode; //출생신고자격코드

  //이메일주소
  private String emailAddress;

  //전화번호
  private String phoneNumber;

}
