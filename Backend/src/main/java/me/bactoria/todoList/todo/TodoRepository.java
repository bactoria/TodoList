package me.bactoria.todoList.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface TodoRepository extends JpaRepository<Todo, Long> {

        @Modifying(clearAutomatically = true)
        @Transactional
        @Query(value = "update Todo todo set todo.isCompletedTodo=true where todo.id = ?1")
        void updatec(Long id);

        @Modifying(clearAutomatically = true)
        @Transactional
        @Query(value= "update Todo todo set todo.title = :title, todo.content = :content, todo.closingDate = :closingDate, todo.priority = :priority where todo.id = :id")
        int updateTodo(@Param("id") Long id, @Param("title") String title, @Param("content") String content, @Param("closingDate") LocalDate closingDate, @Param("priority") Long priority);

        @Query(value = "select * from todo where closing_date < now() and not is_completed_todo;", nativeQuery=true)
        List<Todo> findExpired();
}
