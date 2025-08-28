package dev.myjava.demo;

import org.springframework.stereotype.Component;

@Component
public class TodoRepository {
    String getAllTodos(){
        return "Todos list";
    }
}
