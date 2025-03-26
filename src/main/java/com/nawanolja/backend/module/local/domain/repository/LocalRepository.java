package com.nawanolja.backend.module.local.domain.repository;

import com.nawanolja.backend.module.local.domain.Local;
import com.nawanolja.backend.module.local.domain.vo.LocalType;

import java.util.List;

public interface LocalRepository {
    List<Local> findByLocalType(LocalType localType);
    List<Local> findByFullNameContainingAndLocalTypeAndSggCodeEndingWith(String fullName, LocalType localType, String sggCodeSuffix);
    List<Local> findByFullNameContainingAndLocalType(String fullName, LocalType localType);

}
