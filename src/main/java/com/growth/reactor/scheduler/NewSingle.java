package com.growth.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class NewSingle {

    @SneakyThrows
    public static void main(String[] args) {
        doTask("task1")
                .subscribe(data -> log.info("onNext: {}", data));

        doTask("task2")
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(200L);
    }

    public static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .publishOn(Schedulers.newSingle("new-single", true))
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("{} doOnNext filter: {}", taskName, data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("{} doOnNext map: {}", taskName, data));
    }

}
