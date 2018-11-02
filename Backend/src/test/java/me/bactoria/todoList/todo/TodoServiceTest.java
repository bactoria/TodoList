package me.bactoria.todoList.todo;

import me.bactoria.todoList.todo.dto.UpdateTodoRequestDto;
import me.bactoria.todoList.todo.exception.TodoNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepositoryMock;

    @Test
    public void 특정_Todo를_불러온다() {
        //given
        final Long ID = 1L;
        Optional<Todo> optionalTodo = Optional.ofNullable(Todo.builder().build());
        System.out.println(optionalTodo);
        given(todoRepositoryMock.findById(anyLong())).willReturn(optionalTodo);

        //when
        Todo todo = todoService.getTodo(ID);

        //then
        assertThat(todo).isEqualTo(optionalTodo.orElseThrow(TodoNotFoundException::new));

    }

    @Test
    public void 모든_Todo를_불러온다() {
        //given
        Todo todo1 = Todo.builder().build();
        given(todoRepositoryMock.findAll()).willReturn(Arrays.asList(todo1));

        //when
        List<Todo> todoList = todoService.getTodoAll();

        //then
        assertThat(todoList).isNotEmpty();
    }

    @Test
    public void 특정_Todo를_삭제한다() {
        //given
        final Long ID = 1L;

        //when
        todoService.deleteTodo(ID);

        //then
        verify(todoRepositoryMock, atLeastOnce()).deleteById(ID);
    }

    @Test
    public void 특정_Todo를_수정한다() {
        //given
        final Long ID = 1L;
        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().build();

        //when
        todoService.updateTodoWithTitleAndContentAndClosingDateAndPriority(ID, dto);

        //then
        verify(todoRepositoryMock, atLeastOnce()).updateTodo(ID, dto.getTitle(), dto.getContent(), dto.getClosingDate(), dto.getPriority());
    }

    @Test
    public void 특정_Todo를_완료체크한다() {
        //given
        final Long ID = 1L;

        //when
        todoService.updateCompletedTodo(ID);

        //then
        verify(todoRepositoryMock, atLeastOnce()).updateCompletedTodo(ID);
    }

    @Test
    public void 만료된_모든_Todo를_불러온다() {
        //given

        //when
        todoService.getTodoExpired();

        //then
        verify(todoRepositoryMock, atLeastOnce()).findExpired();
    }

}