package com.skm.accounts.config;

import com.skm.accounts.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@org.springframework.context.annotation.Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistanceConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
