package me.bactoria.todoList;

import me.bactoria.todoList.todo.Todo;
import me.bactoria.todoList.todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class SaveTodoRunner implements ApplicationRunner {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        IntStream.rangeClosed(1,10).forEach( i ->
                todoRepository.save(Todo.builder()
                                                        .title("제목" + i)
                                                        .content("내용" + i)
                                                        .isCompletedTodo(false)
                                                        .build()));
    }
}
