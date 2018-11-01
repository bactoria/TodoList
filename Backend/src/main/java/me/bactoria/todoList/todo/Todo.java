package me.bactoria.todoList.todo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDate closingDate;

    @Column
    private boolean isCompletedTodo;

    @Builder
    public Todo(String title, String content, LocalDate closingDate, boolean isCompletedTodo) {
        this.title = title;
        this.content = content;
        this.closingDate = closingDate;
        this.isCompletedTodo = isCompletedTodo;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", closingDate=" + closingDate +
                ", isCompletedTodo=" + isCompletedTodo +
                '}';
    }
}
