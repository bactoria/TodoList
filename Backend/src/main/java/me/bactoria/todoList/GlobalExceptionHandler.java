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
        return "{\"exception\" : \"TodoNotFoundException\", \"message\" : \"해당 Todo가 존재하지 않습니다.\"}";
    }
}
