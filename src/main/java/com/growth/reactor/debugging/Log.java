package com.growth.reactor.debugging;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Log {

    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("banana", "바나나");
        map.put("apple", "사과");
        map.put("pear", "배");
        map.put("grape", "포도");
        return map;
    }

    @SneakyThrows
    public static void main(String[] args) {

        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .log()
                .map(createMap()::get)
                .subscribe(
                        log::info,
                        error -> log.error("onError: ", error)
                );


    }
}
