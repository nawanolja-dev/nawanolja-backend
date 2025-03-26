package com.nawanolja.backend.module.local.infra.repository;

import com.nawanolja.backend.module.local.domain.Local;
import com.nawanolja.backend.module.local.domain.repository.LocalRepository;
import com.nawanolja.backend.module.local.domain.vo.LocalType;
import com.nawanolja.backend.module.local.infra.repository.jpa.LocalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocalRepositoryImpl implements LocalRepository {

    private final LocalJpaRepository localJpaRepository;

    @Override
    public List<Local> findByLocalType(LocalType localType) {
        return localJpaRepository.findByLocalType(localType);
    }

    @Override
    public List<Local> findByFullNameContainingAndLocalTypeAndSggCodeEndingWith(String fullName, LocalType localType, String sggCodeSuffix) {
        return localJpaRepository.findByFullNameContainingAndLocalTypeAndSggCodeEndingWith(fullName, localType, sggCodeSuffix);
    }

    @Override
    public List<Local> findByFullNameContainingAndLocalType(String fullName, LocalType localType) {
        return localJpaRepository.findByFullNameContainingAndLocalType(fullName, localType);
    }


}
