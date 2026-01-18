package com.growth.reactor.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

@Slf4j
public class ContextAPIExample {

    @SneakyThrows
    public static void main(String[] args) {
        String key1 = "company";
        String key2 = "firstName";
        String key3 = "lastName";

        Mono.deferContextual(ctx ->
                        Mono.just(ctx.get(key1) + ", " + ctx.get(key2) + ", " + ctx.get(key3)))
                .publishOn(Schedulers.parallel())
                .contextWrite(ctx -> ctx.putAll(Context.of(key2, "Steve", key3, "Jobs").readOnly()))
                .contextWrite(ctx -> ctx.put(key1, "Apple Inc."))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);
    }
}
