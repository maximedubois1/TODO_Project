package com.sp.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GetRouterConfig {

    @Bean
    RouteLocator gateway(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(routeSpec -> routeSpec.path("/orchestrator")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://orchestrator"))
                .route(routeSpec -> routeSpec.path("/api/v1/cards/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://card-service"))
                .route(routeSpec -> routeSpec.path("/api/v1/auth/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://auth-service"))
                .route(routeSpec -> routeSpec.path("/api/v1/users/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://auth-service"))
                .route(routeSpec -> routeSpec.path("/api/v1/room/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://room-service"))
                .build();
    }
}
