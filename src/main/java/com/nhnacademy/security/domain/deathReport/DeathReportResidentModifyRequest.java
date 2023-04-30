package com.nhnacademy.security.domain.deathReport;

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
public class DeathReportResidentModifyRequest {


  //출생사망신고일자
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDeathReportDate;

  //사망신고자격코드
  private String deathReportQualificationsCode;

  //이메일주소
  private String emailAddress;

  //전화번호
  private String phoneNumber;
}
