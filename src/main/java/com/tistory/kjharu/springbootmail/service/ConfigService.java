package com.tistory.kjharu.springbootmail.service;

import com.tistory.kjharu.springbootmail.dto.ConfigDto;
import com.tistory.kjharu.springbootmail.repository.ConfigRepo;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    private final ConfigRepo configRepo;

    public ConfigService(ConfigRepo configRepo) {
        this.configRepo = configRepo;
    }

    public ConfigDto getConfig(String key) {
        ConfigDto configDto = configRepo.findByKey(key);
        return configDto;
    }
}
