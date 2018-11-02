package me.bactoria.todoList.todo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.bactoria.todoList.todo.dto.SaveTodoRequestDto;
import me.bactoria.todoList.todo.dto.UpdateTodoRequestDto;
import me.bactoria.todoList.todo.exception.TodoNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoServiceMock;

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void 전체_TODO들을_불러온다() throws Exception {

        //given
        Todo todo1 = Todo.builder().title("제목1").build();
        Todo todo2 = Todo.builder().title("제목2").build();
        List<Todo> todoList = Arrays.asList(todo1, todo2);

        given(todoServiceMock.getTodoAll()).willReturn(todoList);

        //when
        mockMvc.perform(get("/api/todos"))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.todoResourceList[0].todo.title", is("제목1")))
                .andExpect(jsonPath("$._embedded.todoResourceList[1].todo.title", is("제목2")));

    }

    @Test
    public void 만료된_TODO들을_불러온다() throws Exception {

        //given
        Todo todo1 = Todo.builder().title("제목1").build();
        Todo todo2 = Todo.builder().title("제목2").build();
        List<Todo> todoList = Arrays.asList(todo1, todo2);

        given(todoServiceMock.getTodoExpired()).willReturn(todoList);

        //when
        mockMvc.perform(get("/api/todos/expired"))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.todoResourceList[0].todo.title", is("제목1")))
                .andExpect(jsonPath("$._embedded.todoResourceList[1].todo.title", is("제목2")));
    }

    @Test
    public void 특정_TODO를_불러온다() throws Exception {

        //given
        Todo todo = Optional.ofNullable(Todo.builder().title("제목1").build()).orElseThrow(TodoNotFoundException::new);

        given(todoServiceMock.getTodo(anyLong())).willReturn(todo);

        //when
        mockMvc.perform(get("/api/todos/1"))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.todo.title", is("제목1")));
    }

    @Test
    public void TODO를_저장한다() throws Exception {

        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().title("제목1").build();
        Todo todo = dto.toEntity();

        given(todoServiceMock.saveTodo(any(SaveTodoRequestDto.class))).willReturn(todo);

        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("제목1")));
    }

    @Test
    public void TODO를_수정한다() throws Exception {

        //given
        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().title("제목1").build();
        final int ID = 1;

        given(todoServiceMock.updateTodoWithTitleAndContentAndClosingDateAndPriority(anyLong(), any(UpdateTodoRequestDto.class))).willReturn(ID);

        //when
        mockMvc.perform(put("/api/todos/{id}", ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TODO를_완료한다() throws Exception {

        //given
        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().title("제목1").build();
        final Long ID = 1L;

        given(todoServiceMock.updateCompletedTodo(anyLong())).willReturn(Math.toIntExact(ID));

        //when
        mockMvc.perform(put("/api/todos/{id}/completed", ID))

                //then
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TODO를_삭제한다() throws Exception {

        //given
        final Long ID = 1L;

        //when
        mockMvc.perform(delete("/api/todos/{id}", ID))

                //then
                .andDo(print())
                .andExpect(status().isOk());

        verify(todoServiceMock, atLeastOnce()).deleteTodo(ID);
    }
}
