package me.bactoria.todoList;

import me.bactoria.todoList.todo.Todo;
import me.bactoria.todoList.todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Component
public class SaveTodoRunner implements ApplicationRunner {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        IntStream.rangeClosed(1,10).forEach( i -> {
            Long priority = (long) (Math.random()*3) + 1; // 1,2,3 중 랜덤 값
            LocalDate closingDate = LocalDate.now().plusDays((long)((Math.random() - 0.4)*10));

            todoRepository.save(Todo.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .isCompletedTodo(false)
                    .priority(priority)
                    .closingDate(closingDate)
                    .build());
        });
    }
}
