package com.growth.reactor.operators;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class DeferExample {

    @SneakyThrows
    public static void main(String[] args) {
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000L);
        justMono.subscribe(data -> log.info("Just Mono: {}", data));
        deferMono.subscribe(data -> log.info("Defer Mono: {}", data));

        Thread.sleep(2000L);
        justMono.subscribe(data -> log.info("Just Mono: {}", data));
        deferMono.subscribe(data -> log.info("Defer Mono: {}", data));

        Mono.just("Hello")
                .delayElement(Duration.ofSeconds(3))
//                .switchIfEmpty(sayDefault())
                .switchIfEmpty(Mono.defer(DeferExample::sayDefault))
                .subscribe(data -> log.info("Data: {}", data));

        Thread.sleep(3500L);
    }

    private static Mono<String> sayDefault() {
        log.info("Say Hi");
        return Mono.just("Hello");
    }
}
