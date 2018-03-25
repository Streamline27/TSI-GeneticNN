package lv.tti.web;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.Test;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ReactorTest {

    @Test
    public void x() throws InterruptedException {

        EmitterProcessor<Object> stream = EmitterProcessor.create();

        Mono.just(new Object())
                .repeat(5)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(next -> System.out.println("Hello"));


        TimeUnit.SECONDS.sleep(3);

        stream.onComplete();

    }
}
