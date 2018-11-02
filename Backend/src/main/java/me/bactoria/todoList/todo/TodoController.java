package me.bactoria.todoList.todo;

import lombok.extern.slf4j.Slf4j;
import me.bactoria.todoList.todo.dto.SaveTodoRequestDto;
import me.bactoria.todoList.todo.dto.UpdateTodoRequestDto;
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
        log.info("GET :: /api/todos");
        List<TodoResource> todos = todoService.getTodoAll().stream().map(TodoResource::new).collect(Collectors.toList());
        Resources<TodoResource> resources = new Resources<>(todos);
        //       resources.add(linkTo(methodOn(TodoController.class).getTodos()).withSelfRel()); //셀프 링크 추가
        //final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        //resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/expired")
    public ResponseEntity<Resources<TodoResource>> getTodoExpired() {
        log.info("GET :: /api/todos/expired");
        List<TodoResource> todos = todoService.getTodoExpired().stream().map(TodoResource::new).collect(Collectors.toList());
        Resources<TodoResource> resources = new Resources<>(todos);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResource> getTodo(@PathVariable Long id) {
        log.info("GET :: /api/todos/" + id);

        Todo todo = todoService.getTodo(id);
        TodoResource todoResource = new TodoResource(todo);
        return ResponseEntity.ok(todoResource);
    }

    @PostMapping
    public Todo saveTodo(@RequestBody SaveTodoRequestDto dto) {
        log.info("POST :: /api/todos" + "   dto :: " +dto);
        return todoService.saveTodo(dto);
    }

    @PutMapping("/{id}")
    public int updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequestDto dto) {
        log.info("PUT :: /api/todos/" + id + "   dto :: " + dto);
        return todoService.updateTodoWithTitleAndContentAndClosingDateAndPriority(id, dto);
    }

    @PutMapping("/{id}/completed")
    public int updateCompletedTodo(@PathVariable Long id) {
        log.info("PUT :: /api/todos/" + id + "/completed");
        return todoService.updateCompletedTodo(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        log.info("DELETE :: /api/todos/" + id);
        todoService.deleteTodo(id);
    }

}
