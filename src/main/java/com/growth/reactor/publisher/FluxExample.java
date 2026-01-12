package com.growth.reactor.publisher;

import reactor.core.publisher.Flux;

public class FluxExample {

    public static void main(String[] args) {
        Flux.just(5, 10, 15, 20)
                .filter(number -> number % 10 == 0)
                .map(number -> "Number: " + number)
                .subscribe(System.out::println);

        Flux.fromArray(new Integer[]{1, 2, 4, 5, 8, 10})
                .filter(number -> number % 2 == 0)
                .subscribe(System.out::println);

        Flux.fromArray(new Integer[]{1, 2, 4, 5, 8, 10})
                .concatWith(Flux.range(1, 10))
                .subscribe(System.out::println);
    }
}
