package com.sp.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    RouteLocator gateway(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(routeSpec -> routeSpec.path("/orchestrator")
                        .uri("lb://orchestrator"))
                .route(routeSpec -> routeSpec.path("/api/v1/cards/**")
                        .uri("lb://card-service"))
                .route(routeSpec -> routeSpec.path("/api/v1/auth/**")
                        .uri("lb://auth-service"))
                .route(routeSpec -> routeSpec.path("/api/v1/room/**")
                        .uri("lb://room-service"))
                .build();
    }
}
