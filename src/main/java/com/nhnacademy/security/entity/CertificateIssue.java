package com.nhnacademy.security.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Setter;

@Setter
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {

  @Id
  @Column(name = "certicate_confirmation_number")
  private Long certicateConfirmationNumber;

  @ManyToOne
  @JoinColumn(name = "resident_serial_number")
  private Resident resident;

  @Column(name = "certificate_type_code")
  private String certificateTypeCode;

  @Column(name = "certificate_issue_date")
  private LocalDate certificateIssueDate;
}
