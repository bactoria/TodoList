package me.bactoria.todoList.todo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private LocalDate closingDate;

    @Column
    private boolean isCompletedTodo;

    @Column(nullable = false)
    private Long priority;

    @Builder
    public Todo(String title, String content, LocalDate closingDate, boolean isCompletedTodo, Long priority) {
        this.title = title;
        this.content = content;
        this.closingDate = closingDate;
        this.isCompletedTodo = isCompletedTodo;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", closingDate=" + closingDate +
                ", isCompletedTodo=" + isCompletedTodo +
                ", priority=" + priority +
                '}';
    }
}
