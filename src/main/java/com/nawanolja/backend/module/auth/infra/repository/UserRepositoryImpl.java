package com.nawanolja.backend.module.auth.infra.repository;

import com.nawanolja.backend.module.auth.domain.User;
import com.nawanolja.backend.module.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(long id) {
        return userJpaRepository.findById(id);
    }
}
