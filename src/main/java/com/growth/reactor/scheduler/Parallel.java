package com.growth.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Parallel {

    @SneakyThrows
    public static void main(String[] args) {
        Flux.fromArray(new Integer[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23})
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);
    }
}
