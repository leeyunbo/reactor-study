package com.growth.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BoundElastic {

    @SneakyThrows
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(10L))
                .publishOn(Schedulers.boundedElastic())
                .map(data -> data * 2)
                .doOnNext(data -> log.info("doOnNext map: {}", data))
                .filter(value -> value % 2 == 0)
                .doOnNext(data -> log.info("doOnNext filter: {}", data))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(5000L);
    }
}
