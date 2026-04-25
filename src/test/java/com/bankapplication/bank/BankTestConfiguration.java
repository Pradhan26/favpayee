package com.bankapplication.bank;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Test configuration for Bank module tests.
 * This configuration can be used to provide test-specific beans and configurations.
 */
@TestConfiguration
@EnableJpaRepositories(basePackages = "com.bankapplication.bank.repository")
public class BankTestConfiguration {
}

