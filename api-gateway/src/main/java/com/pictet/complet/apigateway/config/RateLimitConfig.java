package com.pictet.complet.apigateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Configuration
public class RateLimitConfig {

    @Bean
    public KeyResolver ipKeyResolver() {

        return exchange -> {
            String host =
                    Optional.ofNullable(exchange.getRequest().getRemoteAddress())
                            .map(addr -> addr.getAddress().getHostAddress())
                            .orElse("unknown");

            return Mono.just(host);
        };
    }

}
