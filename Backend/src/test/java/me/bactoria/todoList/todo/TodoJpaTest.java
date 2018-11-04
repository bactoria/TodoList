package me.bactoria.todoList.todo;

import me.bactoria.todoList.todo.dto.SaveTodoRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoJpaTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void Todo_저장한다() {
        //given
        Todo todo = SaveTodoRequestDto.builder().build().toEntity();

        //when
        todoRepository.save(todo);

        //then
        assertThat(todoRepository.getOne(todo.getId())).isEqualTo(todo);
    }

    @Test
    public void 특정_Todo를_완료처리한다() {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).isCompletedTodo(false).build().toEntity();
        testEntityManager.persist(todo);

        //when
        todoRepository.updateCompletedTodo(todo.getId());

        //then
        assertThat(todoRepository.findById(todo.getId()).orElseThrow(RuntimeException::new).isCompletedTodo()).isTrue();
    }

    @Test
    public void 특정_Todo를_불러온다() {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build().toEntity();
        testEntityManager.persist(todo);

        //when
        Todo getTodo = todoRepository.findById(todo.getId()).orElseThrow(RuntimeException::new);

        //then
        assertThat(getTodo).isEqualTo(todo);
    }

    @Test
    public void 모든_Todo를_불러온다() {
        //given
        Todo todo1 = SaveTodoRequestDto.builder().title("제목1").content("내용1").priority(1L).build().toEntity();
        Todo todo2 = SaveTodoRequestDto.builder().title("제목2").content("내용2").priority(1L).build().toEntity();
        testEntityManager.persist(todo1);
        testEntityManager.persist(todo2);

        //when
        List<Todo> todoList = todoRepository.findAll();

        //then
        assertThat(todoList).hasSize(2);
    }

    @Test
    public void 만료된_모든_Todo를_불러온다() {
        //given
        Todo todo1 = SaveTodoRequestDto.builder().title("제목1").content("내용1").priority(1L).closingDate(LocalDate.now().minusDays(1)).build().toEntity();
        Todo todo2 = SaveTodoRequestDto.builder().title("제목2").content("내용2").priority(1L).closingDate(LocalDate.now()).build().toEntity();
        Todo todo3 = SaveTodoRequestDto.builder().title("제목3").content("내용3").priority(1L).closingDate(LocalDate.now().plusDays(1)).build().toEntity();
        testEntityManager.persist(todo1);
        testEntityManager.persist(todo2);
        testEntityManager.persist(todo3);

        //when
        List<Todo> todoList = todoRepository.findExpired();
        System.out.println(todo2.getClosingDate());
        //then
        assertThat(todoList).hasSize(1);
    }

    @Test
    public void repository가_비어있다() {

        //when
        List<Todo> todoList = todoRepository.findAll();

        //then
        assertThat(todoList).isEmpty();
    }

    @Test
    public void 특정_Todo를_삭제한다() {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build().toEntity();
        testEntityManager.persist(todo);

        //when
        todoRepository.deleteById(todo.getId());

        //then
        assertThat(todoRepository.findAll()).isEmpty();
    }

}