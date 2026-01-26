package com.growth.reactor.operators;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class FromIterableExample {

    @SneakyThrows
    public static void main(String[] args) {

        Flux.fromIterable(List.of(3, 6, 9, 12, 15, 18, 21, 24, 27, 30))
                .filter(number -> number % 3 == 0 && number % 2 == 0)
                .map(number -> "Filtered Even Multiple of 3: " + number)
                .subscribe(log::info);
    }
}
