package com.growth.reactor.backpressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BufferOldest {

    @SneakyThrows
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("emiited by original Flux:{}", data))
                .onBackpressureBuffer(2,
                        dropped -> log.info("onBackpressureBuffer dropped: {}", dropped),
                        BufferOverflowStrategy.DROP_OLDEST)
                .doOnNext(data -> log.info("emiited by buffer:{}", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(data -> {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException ignored) {
                            }
                            log.info("subscribe: {}", data);
                        },
                        error -> log.error("Error occurred: {}", error.getMessage(), error));

        Thread.sleep(2500L);
    }
}
