package com.growth.reactor.hotandcold;

import reactor.core.publisher.Flux;

import java.util.List;

public class ColdSequence {

    public static void main(String[] args) {
        Flux<String> flux = Flux.fromIterable(List.of("A", "B", "C"))
                .map(String::toLowerCase);

        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);
    }
}
