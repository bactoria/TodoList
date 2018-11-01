package me.bactoria.todoList.todo;

import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class TodoResource extends ResourceSupport {

    private final Todo todo;

    public TodoResource(Todo todo) {
        this.todo = todo;
        final Long id = todo.getId();
        add(linkTo(methodOn(TodoController.class).getTodo(id)).withSelfRel());
        add(linkTo(methodOn(TodoController.class).getTodo(id)).withRel("음이건멀까"));
    }
}
