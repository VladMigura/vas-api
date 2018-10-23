package com.bsuir.vas.repository;

import com.bsuir.vas.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    TokenEntity findOneByValue(String value);
}
