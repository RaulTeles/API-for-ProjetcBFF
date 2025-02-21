package com.avanade.demo.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Log4jInitializer {

    private static final Logger logger = LogManager.getLogger(Log4jInitializer.class);

    @PostConstruct
    public void initialize() {
        logger.info("Log4j has been initialized at application startup.");
    }
}