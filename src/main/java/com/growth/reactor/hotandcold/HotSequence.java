package com.growth.reactor.hotandcold;

import com.growth.reactor.util.KoreaTimeClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
public class HotSequence {

    public static void main(String[] args) throws InterruptedException {
        KoreaTimeClient koreaTimeClient = new KoreaTimeClient();
        Mono<String> mono = koreaTimeClient.getWorldTime().cache();
        log.info("Korea time requested");
        mono.subscribe(time -> log.info("Subscriber 1: Korea time is {}", time));

        Thread.sleep(4000);
        mono.subscribe(time -> log.info("Subscriber 1: Korea time is {}", time));

        Thread.sleep(2000);
    }
}
