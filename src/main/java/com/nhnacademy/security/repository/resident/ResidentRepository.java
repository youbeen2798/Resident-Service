package com.nhnacademy.security.repository.resident;

import com.nhnacademy.security.dto.ResidentDto;
import com.nhnacademy.security.entity.Resident;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer>, ResidentRepositoryCustom {

  Optional<ResidentDto> findByLoginId(String loginId);

  List<ResidentDto> findAllBy();

  ResidentDto findByReidentSerialNumber(Integer serialNumber);

  Page<ResidentDto> getAllBy(Pageable pageable);

  Optional<ResidentDto> findResidentByEmail(String email);

//  Optional<ResidentDto> findByEmail(String email);

  Resident findByEmail(String email);


}
