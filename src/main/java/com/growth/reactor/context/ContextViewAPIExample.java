package com.growth.reactor.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextViewAPIExample {

    @SneakyThrows
    public static void main(String[] args) {
        String key1 = "company";
        String key2 = "firstName";
        String key3 = "lastName";

        Mono.deferContextual(ctx ->
                        Mono.just(ctx.get(key1) + ", "
                                + ctx.getOrEmpty(key2).orElse("no firstName")
                                + ", " + ctx.getOrDefault(key3, "no lastName")))
                .publishOn(Schedulers.parallel())
                .contextWrite(ctx -> ctx.put(key1, "Apple Inc."))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);
    }
}
