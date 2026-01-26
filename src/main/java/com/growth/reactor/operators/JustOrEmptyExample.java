package com.growth.reactor.operators;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class JustOrEmptyExample {

    @SneakyThrows
    public static void main(String[] args) {
        Mono.justOrEmpty(null)
                .subscribe(data -> {},
                        error -> {},
                        () -> log.info("onComplete"));
    }
}
