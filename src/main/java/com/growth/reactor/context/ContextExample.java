package com.growth.reactor.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextExample {

    @SneakyThrows
    public static void main(String[] args) {
        // Hello third first
        Mono<String> names = Mono.deferContextual(ctx ->
                        Mono.just("Hello" + " " + ctx.get("lastName")))
                .subscribeOn(Schedulers.boundedElastic())
                .contextWrite(ctx -> ctx.put("lastName", "third"))
                .publishOn(Schedulers.parallel())
                .contextWrite(ctx -> ctx.put("lastName", "second"))
                .transformDeferredContextual(
                        (mono, ctx) -> mono.map(data -> data + " " + ctx.get("lastName"))
                );

        names.contextWrite(ctx -> ctx.put("lastName", "first"))
                .subscribe(data -> log.info("onNext: {}", data));
        names.contextWrite(ctx -> ctx.put("lastName", "first"))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);
    }
}
