package cn.com.agree.aweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

  @GetMapping("/get")
  public Mono<Object> get() {
    return Mono.just("Hello World");
  }

}
