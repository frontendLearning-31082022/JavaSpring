package com.example.helloReactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class FluxUsageTest {

    @Test
    public void fluxTest(){
        Flux<String> strm=Flux.just("1","2","3","4","6");
        strm.subscribe(f->System.out.println(f));
    }
    @Test
    public void fluxFromArray(){
        Flux<String> strm=Flux.fromArray(new String[]{"1","2"});
        StepVerifier.create(strm).expectNext("1").expectNext("2").verifyComplete();
    }
    @Test
    public void fluxCounter(){
        Flux<Integer> strm=Flux.range(1,2);
        StepVerifier.create(strm).expectNext(1).expectNext(2).verifyComplete();
    }
    @Test
    public void fluxCounterByDelay(){
        Flux<Long> strm=Flux.interval(Duration.ofSeconds(2)).take(2);
        StepVerifier.create(strm).expectNext(0L).expectNext(1L).verifyComplete();
    }
    @Test
    public void fluxMerge() throws InterruptedException {
        Flux<Integer> first=Flux.fromArray(new Integer[]{1,3,5}).delayElements(Duration.ofMillis(500)).delaySubscription(Duration.ofMillis(250));
        Flux<Integer> sec=Flux.fromArray(new Integer[]{0,2,4}).delayElements(Duration.ofMillis(500));
        Flux<Integer>third=first.mergeWith(sec);

        third.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }
    @Test
    public void fluxZip() throws InterruptedException {
        Flux<Integer> first=Flux.fromArray(new Integer[]{1,3,5}).delayElements(Duration.ofMillis(500)).delaySubscription(Duration.ofMillis(250));
        Flux<Integer> sec=Flux.fromArray(new Integer[]{0,2,4}).delayElements(Duration.ofMillis(500));
        Flux<Tuple2<Integer, Integer>> third=Flux.zip(first,sec);

        third.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }
    @Test
    public void fluxZipMapped() throws InterruptedException {
        Flux<Integer> first=Flux.fromArray(new Integer[]{1,3,5}).delayElements(Duration.ofMillis(500)).delaySubscription(Duration.ofMillis(250));
        Flux<Integer> sec=Flux.fromArray(new Integer[]{0,2,4}).delayElements(Duration.ofMillis(500));
        Flux<Object> third=Flux.zip(first,sec, (x, y)->x+y);

        third.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }

}
