package me.bactoria.todoList.todo;

import me.bactoria.todoList.todo.dto.SaveTodoRequestDto;
import me.bactoria.todoList.todo.dto.UpdateTodoRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getTodoAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodo(Long id) {
        return todoRepository.findById(id);
    }

    public Todo saveTodo(SaveTodoRequestDto dto) {

        Todo todo = dto.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .closingDate(dto.getClosingDate())
                .isCompletedTodo(dto.isCompletedTodo())
                .priority(dto.getPriority())
                .build()
                .toEntity();

        return todoRepository.save(todo);
    }

    public int updateTodoWithTitleAndContentAndClosingDateAndPriority(Long id, UpdateTodoRequestDto dto) {
        String title = dto.getTitle();
        String content = dto.getContent();
        LocalDate closingDate = dto.getClosingDate();
        Long priority = dto.getPriority();

        return todoRepository.updateTodo(id, title, content, closingDate, priority);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public int updateCompletedTodo(Long id) {
        return todoRepository.updateCompletedTodo(id);
    }

    public List<Todo> getTodoExpired() {

        return todoRepository.findExpired();
    }
}
