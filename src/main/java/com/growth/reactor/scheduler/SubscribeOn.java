package com.growth.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SubscribeOn {

    @SneakyThrows
    public static void main(String[] args) {
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> log.info("doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("doOnSubscribe"))
                .subscribe(data -> log.info("onNext: {}", data));


        Thread.sleep(500L);
    }
}
