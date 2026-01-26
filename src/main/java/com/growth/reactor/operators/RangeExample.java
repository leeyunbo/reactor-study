package com.growth.reactor.operators;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class RangeExample {

    @SneakyThrows
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(number -> number % 2 == 0)
                .map(number -> "Even Number: " + number)
                .subscribe(log::info);
    }
}
