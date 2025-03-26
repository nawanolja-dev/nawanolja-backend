package com.nawanolja.backend.module.local.infra.repository.jpa;

import com.nawanolja.backend.module.local.domain.Local;
import com.nawanolja.backend.module.local.domain.vo.LocalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalJpaRepository extends JpaRepository<Local, Long> {
    List<Local> findByLocalType(LocalType localType);
    List<Local> findByFullNameContainingAndLocalTypeAndSggCodeEndingWith(String fullName, LocalType localType, String sggCodeSuffix);
    List<Local> findByFullNameContainingAndLocalType(String fullName, LocalType localType);


}
