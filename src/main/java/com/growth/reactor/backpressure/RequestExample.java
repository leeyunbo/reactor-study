package com.growth.reactor.backpressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class RequestExample {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnRequest(data -> log.info("Request {} data", data))
                .subscribe(new BaseSubscriber<>() {

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        Thread.sleep(2000L);
                        log.info("hookOnNext: {}", value);
                        request(1);
                    }

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }
                });
    }
}
