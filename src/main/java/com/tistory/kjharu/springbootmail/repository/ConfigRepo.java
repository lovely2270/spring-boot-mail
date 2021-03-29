package com.tistory.kjharu.springbootmail.repository;

import com.tistory.kjharu.springbootmail.dto.ConfigDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepo extends JpaRepository<ConfigDto, String> {

    ConfigDto findByKey(String key);
}
