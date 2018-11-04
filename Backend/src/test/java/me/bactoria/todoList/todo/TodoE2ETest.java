package me.bactoria.todoList.todo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.bactoria.todoList.todo.dto.SaveTodoRequestDto;
import me.bactoria.todoList.todo.dto.UpdateTodoRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest

public class TodoE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Before
    public void setup() {
        Todo todo = SaveTodoRequestDto.builder()
                .title("제목1")
                .content("내용1")
                .isCompletedTodo(false)
                .priority(1L)
                .closingDate(LocalDate.now())
                .build()
                .toEntity();
        todoRepository.save(todo);
    }

    @Test
    public void 모든_Todo_불러온다() throws Exception {
        //given
        //when
        mockMvc.perform(get("/api/todos"))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.todoResourceList[0].todo.title", is("제목1")));
    }

    @Test
    public void 제목이_없는_Todo_저장요청시_BadRequest() throws Exception {
        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().content("내용").priority(1L).build();
        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("제목을 작성해주세요.")));
    }

    @Test
    public void 내용이_없는_Todo_저장요청시_BadRequest() throws Exception {
        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().title("제목").priority(1L).build();
        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("내용을 작성해주세요.")));
    }

    @Test
    public void 우선순위가_없는_Todo_저장요청시_BadRequest() throws Exception {
        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().title("제목").content("내용").build();
        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("우선순위를 입력해주세요.")));
    }

    @Test
    public void 우선순위가_0인_Todo_저장요청시_BadRequest() throws Exception {
        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().title("제목").content("내용").priority(0L).build();
        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("우선순위는 1~3 사이의 값이어야 합니다.")));
    }

    @Test
    public void 우선순위가_4인_Todo_저장요청시_BadRequest() throws Exception {
        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().title("제목").content("내용").priority(4L).build();
        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("우선순위는 1~3 사이의 값이어야 합니다.")));
    }

    @Test
    public void Completed가_비어있는_Todo_저장하면_False로_저장() throws Exception {
        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build();
        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completedTodo", is(false)));
    }

    @Test
    public void 제목_내용_우선순위가_있는_Todo_저장한다() throws Exception {
        //given
        SaveTodoRequestDto dto = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build();
        //when
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 존재하지않는_Todo_요청시_TodoNotFoundException() throws Exception {
        //given
        //when
        mockMvc.perform(get("/api/todos/9999"))

                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exception", is("TodoNotFoundException")));
    }


    @Test
    public void 제목이_없는_Todo_수정요청시_BadRequest() throws Exception {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build().toEntity();
        todoRepository.save(todo);

        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().content("내용").priority(1L).build();

        //when
        mockMvc.perform(put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("제목을 작성해주세요.")));
    }


    @Test
    public void 내용이_없는_Todo_수정요청시_BadRequest() throws Exception {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build().toEntity();
        todoRepository.save(todo);

        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().title("제목").priority(1L).build();

        //when
        mockMvc.perform(put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("내용을 작성해주세요.")));
    }


    @Test
    public void 우선순위가_없는_Todo_수정요청시_BadRequest() throws Exception {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build().toEntity();
        todoRepository.save(todo);

        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().title("제목").content("내용").build();

        //when
        mockMvc.perform(put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("우선순위를 입력해주세요.")));
    }

    @Test
    public void 우선순위가_0인_Todo_수정요청시_BadRequest() throws Exception {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(1L).build().toEntity();
        todoRepository.save(todo);

        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().title("제목").content("내용").priority(0L).build();

        //when
        mockMvc.perform(put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("우선순위는 1~3 사이의 값이어야 합니다.")));
    }

    @Test
    public void 우선순위가_4인_Todo_수정요청시_BadRequest() throws Exception {
        //given
        Todo todo = SaveTodoRequestDto.builder().title("제목").content("내용").priority(4L).build().toEntity();
        todoRepository.save(todo);

        UpdateTodoRequestDto dto = UpdateTodoRequestDto.builder().title("제목").content("내용").priority(0L).build();

        //when
        mockMvc.perform(put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStringFromObject(dto)))

                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("우선순위는 1~3 사이의 값이어야 합니다.")));
    }

    @After
    public void cleanup() {
        todoRepository.deleteAll();
    }

}
