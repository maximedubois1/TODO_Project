package com.sp.config;

import com.sp.dtos.AuthDTO;
import com.sp.dtos.AuthDtoToOrch;
import com.sp.dtos.ToOrch;
import com.sp.utils.GatewayUtils;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

@Configuration
public class PostRouterConfig {

    @Bean
    RouteLocator userGateway(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()

                .route(routeSpec -> routeSpec.path("/api/v1/auth/orch")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f.modifyRequestBody(AuthDTO.class, AuthDTO.class,
                                        (exchange, authFromOrch) -> Mono.just(GatewayUtils.printValues(authFromOrch)))
                                .setPath("/api/v1/auth/register"))
                        .uri("lb://auth-service"))

                .route(routeSpec -> routeSpec.path("/api/v1/auth/register")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f.modifyRequestBody(AuthDTO.class, AuthDtoToOrch.class,
                                        (exchange, authDTO) -> Mono.just(GatewayUtils.convertAuthDtoForOrchestrator(authDTO)))
                                .setPath("/engine-rest/process-definition/key/register/start"))
                        .uri("lb://orchestrator"))
                .build();
    }
}

