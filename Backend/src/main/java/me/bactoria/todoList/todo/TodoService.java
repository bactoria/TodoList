package me.bactoria.todoList.todo;

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

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public int updateTodoWithTitleAndContentAndClosingDate(Long id, Todo todo) {
        String title = todo.getTitle();
        String content = todo.getContent();
        LocalDate closingDate = todo.getClosingDate();
        return todoRepository.updateTodo(id, title, content, closingDate);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public void updateC(Long id) {
        todoRepository.updatec(id);
    }

}
