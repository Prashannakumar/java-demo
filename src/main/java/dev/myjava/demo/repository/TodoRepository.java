package dev.myjava.demo.repository;

import dev.myjava.demo.models.Todo;
import dev.myjava.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Component
//public class TodoRepository {
//    String getAllTodos(){
//        return "Todos list";
//    }
//}

// CRUD
public interface TodoRepository extends JpaRepository<Todo, Long> {

}