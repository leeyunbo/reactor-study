package com.growth.reactor.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class SinkMany {

    private static class UniCast {
        public static void main(String[] args) {

            Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
            Flux<Integer> fluxView = unicastSink.asFlux();

            unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
            unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

            fluxView.subscribe(data -> log.info("Subscriber1: {}", data));

            unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

            fluxView.subscribe(data -> log.info("Subscriber2: {}", data));
        }
    }

    private static class MultiCast {
        public static void main(String[] args) {

            Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
            Flux<Integer> fluxView = multicastSink.asFlux();

            multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);

            fluxView.subscribe(data -> log.info("Subscriber1: {}", data));

            multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

            fluxView.subscribe(data -> log.info("Subscriber2: {}", data));
            fluxView.subscribe(data -> log.info("Subscriber3: {}", data));

            multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
        }
    }

    private static class Replay {
        public static void main(String[] args) {

            Sinks.Many<Integer> replaySink = Sinks.many().replay().limit(2);
            Flux<Integer> fluxView = replaySink.asFlux();

            replaySink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
            replaySink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
            replaySink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

            fluxView.subscribe(data -> log.info("Subscriber1: {}", data));

            replaySink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);

            fluxView.subscribe(data -> log.info("Subscriber2: {}", data));
            fluxView.subscribe(data -> log.info("Subscriber3: {}", data));
        }
    }
}
