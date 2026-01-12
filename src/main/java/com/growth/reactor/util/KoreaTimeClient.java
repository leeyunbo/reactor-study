package com.growth.reactor.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
public class KoreaTimeClient {

    private final WebClient webClient = WebClient.create();
    private final URI worldTimeUri = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("worldtimeapi.org")
            .path("/api/timezone/Asia/Seoul")
            .build()
            .encode()
            .toUri();

    public Mono<String> getWorldTime() {
        return webClient.get()
                .uri(worldTimeUri)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    DocumentContext json = JsonPath.parse(response);
                    return json.read("$.datetime", String.class);
                });
    }
}
