package dev.myjava.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/")
    String sayHello() {
        return "Hello World, this is from Spring Boot ";
    }
}
