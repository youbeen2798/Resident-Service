package com.nhnacademy.security.repository.certificateIssue;

import com.nhnacademy.security.entity.CertificateIssue;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CertificateIssueRepositoryImpl extends QuerydslRepositorySupport implements CertificateIssueRepositoryCustom {

  public CertificateIssueRepositoryImpl() {
    super(CertificateIssue.class);
  }
}
