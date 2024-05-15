// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.cache.guava.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CacheConfig {
    @Bean
    public Cache<String, Boolean> cache() {
        return CacheBuilder.newBuilder().expireAfterAccess(Duration.ofMinutes(5)).build();
    }
}
