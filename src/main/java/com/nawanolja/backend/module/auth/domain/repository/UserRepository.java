package com.nawanolja.backend.module.auth.domain.repository;

import com.nawanolja.backend.module.auth.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
}
