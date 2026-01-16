package com.growth.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Immediate {

    @SneakyThrows
    public static void main(String[] args) {

        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("doOnNext filter: {}", data))
                .publishOn(Schedulers.immediate())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("doOnNext map: {}", data))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(200L);

    }
}
