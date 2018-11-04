package me.bactoria.todoList.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bactoria.todoList.todo.Todo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UpdateTodoRequestDto {

    @NotBlank(message = "제목을 작성해주세요.")
    private String title;

    @NotBlank(message = "내용을 작성해주세요.")
    private String content;

    @NotNull(message = "우선순위를 입력해주세요.")
    @Min(value = 1, message = "우선순위는 1~3 사이의 값이어야 합니다.")
    @Max(value = 3, message = "우선순위는 1~3 사이의 값이어야 합니다.")
    private Long priority;

    private LocalDate closingDate;

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
