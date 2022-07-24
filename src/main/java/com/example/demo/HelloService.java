package com.example.demo;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class HelloService {

    private final ExecutorService executors;
    private final Scheduler scheduler;

    public HelloService() {

        this.executors = Executors.newFixedThreadPool(100);
        this.scheduler = Schedulers.fromExecutorService(this.executors);
    }

    public CompletableFuture<String> getMassage() {
        return CompletableFuture.supplyAsync(() -> {
//            throwError();
            System.out.println(Thread.currentThread().getName() + "   blocked");
            sleep(100);
            return "Tamajit";

        }, executors);
                /*.handle((a,e)->{
                    if(e!=null)  {
                        throw new CustomException(e.getCause());
                    }
                    return a;
                });*/
               /* .thenApply(s -> {
                    int a = 1 / 0;
                    System.out.println("hello");
                    return s;
                });*/
    }

    private void throwError() {
        int a = 1 / 0;
    }

    public String getMassage2() {
        System.out.println(Thread.currentThread().getName() + "   blocked");
//        throwError();
        sleep(100);
        return "hello";
    }


    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Mono<String> getMassage3() {
        return Mono.just("mono").subscribeOn(scheduler).doOnNext((s) -> {
//            throwError();
            System.out.println(Thread.currentThread().getName() + "   blocked " + System.currentTimeMillis() / 1000);
            sleep(100);
        });
    }
}
