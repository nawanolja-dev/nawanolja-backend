package com.nawanolja.backend.module.auth.infra.repository;


import com.nawanolja.backend.module.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
