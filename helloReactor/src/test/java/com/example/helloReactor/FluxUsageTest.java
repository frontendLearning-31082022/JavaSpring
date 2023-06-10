package com.example.helloReactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.context.Context;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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
    @Test
    public void fluxFirstWithSignal() throws InterruptedException {
        Flux<Integer> first=Flux.fromArray(new Integer[]{1,3,5}).delayElements(Duration.ofMillis(500)).delaySubscription(Duration.ofMillis(250));
        Flux<Integer> sec=Flux.fromArray(new Integer[]{0,2,4}).delayElements(Duration.ofMillis(500));
        Flux<Object> third=Flux.firstWithSignal(first,sec);

        third.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }
    @Test
    public void fluxSkip() throws InterruptedException {
        Flux<Integer> first=Flux.fromArray(new Integer[]{1,3,5}).skip(1);
        first.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }

    //.filter

    @Test
    public void flatMapAsynchParallel() throws InterruptedException {
        Flux<Integer> first=Flux.fromArray(new Integer[]{1,3,5})
                .flatMap(x->
                        Mono.just(x)
                                .map(y->y*2)
                                .subscribeOn(Schedulers.parallel()) ) ;

        first.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }
    @Test
    public void simpleFluxBuffer() throws InterruptedException {
        Flux<Integer> first=Flux.fromArray(new Integer[]{1,3,5,5,8,2,4,1,4,6,87});
        Flux<List<Integer>> buffered=first.buffer(3);

        buffered.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }
    @Test
    public void fluxBufferAsynchParallel() throws InterruptedException {

        String[] arr={"	1	 el",
                "	2	 el",
                "	3	 el",
                "	4	 el",
                "	5	 el",
                "	6	 el",
                "	7	 el",
                "	8	 el",
                "	9	 el",
                "	10	 el",
                "	11	 el",
                "	12	 el",
                "	13	 el",
                "	14	 el",
                "	15	 el",
                "	16	 el",
                "	17	 el",
                "	18	 el",
                "	19	 el",
                "	20	 el",
                "	21	 el",
                "	22	 el",
                "	23	 el",
                "	24	 el",
                "	25	 el",
                "	26	 el",
                "	27	 el",
                "	28	 el",
                "	29	 el",
                "	30	 el",
                "	31	 el",
                "	32	 el",
                "	33	 el",
                "	34	 el",
                "	35	 el",
                "	36	 el",
                "	37	 el",
                "	38	 el",
                "	39	 el",
                "	40	 el",
                "	41	 el",
                "	42	 el",
                "	43	 el",
                "	44	 el",
                "	45	 el",
                "	46	 el",
                "	47	 el",
                "	48	 el",
                "	49	 el",
                "	50	 el",
                "	51	 el",
                "	52	 el",
                "	53	 el",
                "	54	 el",
                "	55	 el",
                "	56	 el",
                "	57	 el",
                "	58	 el",
                "	59	 el",
                "	60	 el",
                "	61	 el",
                "	62	 el",
                "	63	 el",
                "	64	 el",
                "	65	 el",
                "	66	 el",
                "	67	 el",
                "	68	 el",
                "	69	 el",
                "	70	 el",
                "	71	 el",
                "	72	 el",
                "	73	 el",
                "	74	 el",
                "	75	 el",
                "	76	 el",
                "	77	 el",
                "	78	 el",
                "	79	 el",
                "	80	 el",
                "	81	 el",
                "	82	 el",
                "	83	 el",
                "	84	 el",
                "	85	 el",
                "	86	 el",
                "	87	 el",
                "	88	 el",
                "	89	 el",
                "	90	 el",
                "	91	 el",
                "	92	 el",
                "	93	 el",
                "	94	 el",
                "	95	 el",
                "	96	 el",
                "	97	 el",
                "	98	 el",
                "	99	 el",
                "	100	 el",
                "	101	 el",
                "	102	 el",
                "	103	 el",
                "	104	 el",
                "	105	 el",
                "	106	 el",
                "	107	 el",
                "	108	 el",
                "	109	 el",
                "	110	 el",
                "	111	 el",
                "	112	 el",
                "	113	 el",
                "	114	 el",
                "	115	 el",
                "	116	 el",
                "	117	 el",
                "	118	 el",
                "	119	 el",
                "	120	 el",
                "	121	 el",
                "	122	 el",
                "	123	 el",
                "	124	 el",
                "	125	 el",
                "	126	 el",
                "	127	 el",
                "	128	 el",
                "	129	 el",
                "	130	 el",
                "	131	 el",
                "	132	 el",
                "	133	 el",
                "	134	 el",
                "	135	 el",
                "	136	 el"};

        Flux<String> first=Flux.fromArray(arr)
                .buffer(3)
                .flatMap(x->
                        Flux.fromIterable(x)
                                .map(y->y+y)
                                .subscribeOn(Schedulers.parallel())
                                .log()    ) ;
        first.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }

    @Test
    public void collectMapGenerator() throws InterruptedException {
        Flux<String> first=Flux.fromArray(new String[]{"Module1","Module2","Module3"});

       Mono<Map<String,String>> genMapMono=first.collectMap(x->"El"+x.toString().replaceAll("\\D",""));

        genMapMono.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }

    @Test
    public void subscribeOnPublishOn() throws InterruptedException {
        Scheduler schedulerA = Schedulers.newParallel("Scheduler A");
        Scheduler schedulerB = Schedulers.newParallel("Scheduler B");
        Scheduler schedulerC = Schedulers.newParallel("Scheduler C");

        Flux.just(1)
                .map(i -> {
                    System.out.println("First map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerA)
                .map(i -> {
                    System.out.println("Second map: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(schedulerB)
                .map(i -> {
                    System.out.println("Third map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerC)
                .map(i -> {
                    System.out.println("Fourth map: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(schedulerA)
                .map(i -> {
                    System.out.println("Fifth map: " + Thread.currentThread().getName());
                    return i;
                })
                .blockLast();
    }

    @Test
    public void coldPublisher() throws InterruptedException {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
        Thread.sleep(2000);
        intervalFlux.subscribe(i -> System.out.println(String.format("Subscriber A, value: %d", i)));
        Thread.sleep(2000);
        intervalFlux.subscribe(i -> System.out.println(String.format("Subscriber B, value: %d", i)));
        Thread.sleep(10000);
    }
    @Test
    public void hotPublisher() throws InterruptedException {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));

        ConnectableFlux<Long> intervalCF = intervalFlux.publish();
        intervalCF.connect();

        Thread.sleep(2000);
        intervalCF.subscribe(i -> System.out.println(String.format("Subscriber A, value: %d", i)));
        Thread.sleep(2000);
        intervalCF.subscribe(i -> System.out.println(String.format("Subscriber B, value: %d", i)));
        Thread.sleep(10000);
    }

    @Test
    public void forSynchBlockingInput() throws InterruptedException {
        Mono blockingWrapper = Mono.fromCallable(() -> {
            return null;/* make a remote synchronous call */
        });
        blockingWrapper = blockingWrapper.subscribeOn(Schedulers.boundedElastic());
    }

    @Test
    public void putContextOnMono() throws InterruptedException {
        String key = "_cntxValue_";
        Mono<String> mono = Mono.just("anything")
                .flatMap(x->
                        Mono.deferContextual(ctx->
                               Mono.just(String.format("%s %s",x,ctx.get("cnt_key")))  )
                      )
                .contextWrite(Context.of("cnt_key", key));

        mono.subscribe(f->System.out.println(f));
        Thread.sleep(5000);
    }

//    @Test
    public void generatorStrings() throws InterruptedException {
        Flux.<String>generate(x->x.next("word"))
                .subscribe(System.out::println);
    }
    @Test
    public void generatorCounter() throws InterruptedException {
        Flux.generate(()->0,
                        (state, sink) -> {
                            if (state>15){sink.complete();
                            }else {sink.next("Step "+state); }

                            return state+3;
                        })
                .timeout(Duration.ofSeconds(2))
                .onErrorReturn("Slowww")
                .retry(5)
                .subscribe(System.out::println);
    }
    @Test
    public void fluxSubscribeOnFlux() throws InterruptedException {
        Flux<Object> flowFlux= Flux.generate(()->0,
                        (state, sink) -> {
                            if (state>15){sink.complete();
                            }else {sink.next("Step "+state); }

                            return state+3;
                        });

        Flux.create(sink->
                flowFlux.subscribe(new BaseSubscriber<Object>() {
                    @Override
                    protected void hookOnNext(Object msg) {
                        sink.next(msg);
                    }

                    @Override
                    protected void hookOnComplete() {
                        sink.complete();
                    }
                })
                )
                .subscribe(System.out::println);
    }

    @Test
    public void generatorStrings11() throws InterruptedException {
        Flux.<String>generate(x->x.next("word"))
                .subscribe(System.out::println);
    }


}
