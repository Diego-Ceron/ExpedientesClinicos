package com.expedientesclinicos.repository.auth;

import com.expedientesclinicos.model.auth.EmailLoginCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface EmailLoginCodeRepository extends JpaRepository<EmailLoginCode, Long> {
    Optional<EmailLoginCode> findTopByEmailOrderByCreatedAtDesc(String email);
    Optional<EmailLoginCode> findByEmailAndCodeAndUsedFalseAndExpiresAtAfter(String email, String code, Instant now);
    void deleteByEmail(String email);
}
