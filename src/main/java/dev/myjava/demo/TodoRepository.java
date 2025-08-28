package dev.myjava.demo;

import dev.myjava.demo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

//@Component
//public class TodoRepository {
//    String getAllTodos(){
//        return "Todos list";
//    }
//}

// CRUD
public interface TodoRepository extends JpaRepository<Todo, Long> {

}