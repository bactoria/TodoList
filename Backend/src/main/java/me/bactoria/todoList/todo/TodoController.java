package me.bactoria.todoList.todo;

import lombok.extern.slf4j.Slf4j;
import me.bactoria.todoList.todo.exception.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/todos", produces = "application/hal+json")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public ResponseEntity<Resources<TodoResource>> getTodoAll() {

        List<TodoResource> todos = todoService.getTodoAll().stream().map(TodoResource::new).collect(Collectors.toList());
        Resources<TodoResource> resources = new Resources<>(todos);
        //       resources.add(linkTo(methodOn(TodoController.class).getTodos()).withSelfRel()); //셀프 링크 추가
        //final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        //resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResource> getTodo(@PathVariable Long id) {

        Todo todo = todoService.getTodo(id).orElseThrow(TodoNotFoundException::new);
        TodoResource todoResource = new TodoResource(todo);
        return ResponseEntity.ok(todoResource);
    }

    @PostMapping
    public Todo saveTodo(@RequestBody Todo todo) {
        log.info("POST :: /api/todos" + "   Todo :: " + todo);
        return todoService.saveTodo(todo);
    }

    @PutMapping("/{id}")
    public int updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        log.info("PUT :: /api/todos/" + id  + "   Todo :: " + todo);
        return todoService.updateTodoWithTitleAndContentAndClosingDateAndPriority(id, todo);
    }

    @PutMapping("/{id}/completed")
    public void updateC(@PathVariable Long id) {
        log.info("PUT :: /api/todos/" + id + "/completed");
        todoService.updateC(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        log.info("DELETE :: /api/todos/" + id);
        todoService.deleteTodo(id);
    }

}
