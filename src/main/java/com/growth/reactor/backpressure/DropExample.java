package com.growth.reactor.backpressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class DropExample {

    @SneakyThrows
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureDrop(dropped -> log.info("onBackpressureDrop: {}", dropped))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            try {
                                Thread.sleep(5L);
                            } catch (InterruptedException ignored) {}
                            log.info("subscribe: {}", data);
                        },
                        error -> log.error("Error occurred: {}", error.getMessage(), error));

        Thread.sleep(2000L);
    }
}
