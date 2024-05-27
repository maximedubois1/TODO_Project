package com.sp.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/card")
public class uCardServiceProxy {

    private static final String CARD_SERVICE = "card-service";

    private final WebClient webClient;
    private final EurekaClient discoveryClient;

    public uCardServiceProxy(WebClient webClient, EurekaClient discoveryClient) {
        this.webClient = webClient;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    public Mono<ResponseEntity<Void>> getCards() {
        String baseUrl =
                discoveryClient.getNextServerFromEureka(CARD_SERVICE, false)
                        .getHomePageUrl();

        return webClient.get()
                .uri(baseUrl + "/api/v1/cards")
                .retrieve()
                .toBodilessEntity();
    }

    @GetMapping
    public Mono<ResponseEntity<Void>> getCardsByUserId(Long id) {
        String baseUrl =
                discoveryClient.getNextServerFromEureka(CARD_SERVICE, false)
                        .getHomePageUrl();

        return webClient.get()
                .uri(baseUrl + "/api/v1/cards/user/" + id)
                .retrieve()
                .toBodilessEntity();
    }

    @GetMapping
    public Mono<ResponseEntity<Void>> getCardsOnMarket() {
        String baseUrl =
                discoveryClient.getNextServerFromEureka(CARD_SERVICE, false)
                        .getHomePageUrl();

        return webClient.get()
                .uri(baseUrl + "/api/v1/cards/market")
                .retrieve()
                .toBodilessEntity();
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> addCardToUser(Long cardId, Long userId) {
        String baseUrl =
                discoveryClient.getNextServerFromEureka(CARD_SERVICE, false)
                        .getHomePageUrl();

        return webClient.post()
                .uri(baseUrl + "/api/v1/cards/buy/" + cardId + "/to-user/" + userId)
                .retrieve()
                .toBodilessEntity();
    }

//    @PostMapping("v1/orders")
//    public Mono<ResponseEntity<Void>> ordersV1() {
//
//        String baseUrl =
//                discoveryClient.getNextServerFromEureka(ORDER_SERVICE, false)
//                        .getHomePageUrl();
//
//        return webClient.post()
//                .uri(baseUrl + "/orders")
//                .body(Mono.just(order), Order.class)
//                .retrieve()
//                .toBodilessEntity();
//    }

}
