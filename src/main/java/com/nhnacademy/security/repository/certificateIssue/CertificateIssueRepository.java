package com.nhnacademy.security.repository.certificateIssue;

import com.nhnacademy.security.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long>, CertificateIssueRepositoryCustom {

}
