package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController

public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/greet")
    public CompletableFuture<String> hello(HttpServletRequest request) {
        return helloService.getMassage();
       /* .handle((s,ex)->{
            try {
                throw ex;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return s;
        });*/
    }

    @GetMapping("/greet2")
    public String hello2(HttpServletRequest request) {
        return helloService.getMassage2();
    }

    @GetMapping("/greet3")
    public Mono<String> hello3(HttpServletRequest request) {
        Mono<String> massage3 = helloService.getMassage3();
        System.out.println(Thread.currentThread().getName()+" returned  " +System.currentTimeMillis()/1000);
        return massage3;
    }
}
