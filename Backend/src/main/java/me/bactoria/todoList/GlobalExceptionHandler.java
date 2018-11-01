package me.bactoria.todoList;

import me.bactoria.todoList.todo.exception.TodoNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // 빈등록
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public @ResponseBody
    String todoNotFoundException(TodoNotFoundException e) {
        return "{\"message\" : \"error.엥.앵\", \"reason\" : \"이유는이유. HELLO\"}";
    }
}
