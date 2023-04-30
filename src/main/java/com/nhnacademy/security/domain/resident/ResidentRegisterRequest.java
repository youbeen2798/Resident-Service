package com.nhnacademy.security.domain.resident;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
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
public class ResidentRegisterRequest {


  @NotEmpty
  private String name; //성명

  @NotEmpty
  private String residentRegistrationNumber; //주민등록번호

  @NotEmpty
  private String genderCode; //성별코드

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
  private LocalDateTime birthDate; //출생일시

  @NotEmpty
  private String birthPlaceCode; //출생장소코드

  @NotEmpty
  private String registrationBaseAddress; //등록기준지주소
}
