package me.bactoria.todoList.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bactoria.todoList.todo.Todo;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UpdateTodoRequestDto {

    private String title;
    private String content;
    private LocalDate closingDate;
    private Long priority;

    @Builder
    public UpdateTodoRequestDto(String title, String content, LocalDate closingDate, Long priority) {
        this.title = title;
        this.content = content;
        this.closingDate = closingDate;
        this.priority = priority;
    }

    public Todo toEntity() {
        return Todo.builder()
                .title(this.title)
                .content(this.content)
                .closingDate(this.closingDate)
                .priority(this.priority)
                .build();
    }
}
