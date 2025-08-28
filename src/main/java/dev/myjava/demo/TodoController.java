package dev.myjava.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
//    @GetMapping("/todo")
    @GetMapping("/")
    String GetTodo(){
        todoService.printTodos();
        return "Todo";
    }

//    @GetMapping("/todo/id")
    // Path Variable
    @GetMapping("/{id}")
    String GetTodoById(@PathVariable int id){
        return "Todo with Id " + id;
    }

    // Request Param
    @GetMapping
//    String getTodoByIdParam(@RequestParam long id) {
//        return "Todo with param id " + id;
//    }
//    String getTodoByIdParam(@RequestParam("todoId") long id) {
//        return "Todo with param id " + id;
//    }
    String getTodoByIdParam(@RequestParam(name = "todoId") long id) {
        return "Todo with param id " + id;
    }

    @PostMapping
    String createUser(@RequestBody String body){
        return body;
    }

    @PutMapping("/update/{id}")
    String updateUser(@PathVariable String id, @RequestBody String body) {
        return "Update " +id + " with body " + body;
    }

    @DeleteMapping("/{id}")
    String deleteTodo(@PathVariable int id){
        return "Delete " + id;
    }
}
