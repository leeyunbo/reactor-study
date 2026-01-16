package com.growth.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FromExecutorService {

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .subscribeOn(Schedulers.fromExecutorService(executorService))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("doOnNext: {}", data))
                .filter(value -> value % 2 == 0)
                .doOnNext(data -> log.info("doOnNext: {}", data))
                .subscribe(data -> log.info("onNext: {}", data));

        executorService.shutdown();
    }
}
