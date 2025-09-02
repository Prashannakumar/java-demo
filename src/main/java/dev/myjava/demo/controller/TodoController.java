package dev.myjava.demo.controller;

import dev.myjava.demo.service.TodoService;
import dev.myjava.demo.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@Slf4j
public class TodoController {
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;
//    @GetMapping("/todo")
    @GetMapping("/")
    ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
    }
//    String GetTodo(){
//        todoService.printTodos();
//        return "Todo";
//
//    }

//    @GetMapping("/todo/id")
    // Path Variable
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo Retrieved Successfully!"),
            @ApiResponse(responseCode = "404", description = "Todo Not Found!")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> GetTodoById(@PathVariable long id){
        try{
            Todo _todo = todoService.getTodoById(id);
            return new ResponseEntity<>(_todo, HttpStatus.OK);
        } catch(RuntimeException exception) {
            log.info("run..run baby run");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
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

    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodoPages(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(todoService.getTodoPage(page, size), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        try{
            Todo _todo = todoService.updateTodo(todo);
            return new ResponseEntity<>(_todo, HttpStatus.ACCEPTED);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
//    String updateUser(@PathVariable String id, @RequestBody String body) {
//        return "Update " +id + " with body " + body;
//    }

    @DeleteMapping("/{id}")
    void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }
}
