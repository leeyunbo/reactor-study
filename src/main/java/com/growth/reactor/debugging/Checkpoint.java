package com.growth.reactor.debugging;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Checkpoint {

    @SneakyThrows
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("Before adding 2", true)
                .map(num -> num + 2)
                .checkpoint("After adding 2", true)
                .subscribe(
                        data -> log.info("onNext: {}", data),
                        error -> log.error("onError: ", error)
                );

        Thread.sleep(100L);
    }
}
