package me.bactoria.todoList;

import lombok.Getter;
import me.bactoria.todoList.todo.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler
    public ResponseEntity<String> asd(MethodArgumentNotValidException e) {

        ErrorDetails errorDetails = new ErrorDetails(e.getBindingResult().getFieldError().getField(), e.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Getter
    private class ErrorDetails {

        private String field;
        private String message;

        public ErrorDetails(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorDetails{" +
                    "errorField='" + field + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
