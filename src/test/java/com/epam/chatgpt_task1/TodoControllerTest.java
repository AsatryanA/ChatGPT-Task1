package com.epam.chatgpt_task1;

import com.epam.chatgpt_task1.controller.TodoController;
import com.epam.chatgpt_task1.entity.Todo;
import com.epam.chatgpt_task1.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    void getAllTodos() {
        // Given
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1L, "Task 1", "Description 1"));
        todos.add(new Todo(2L, "Task 2", "Description 2"));

        when(todoService.getAllTodos()).thenReturn(todos);

        // When
        List<Todo> result = todoController.getAllTodos();

        // Then
        assertThat(result).isEqualTo(todos);
    }

    @Test
    void getTodoById() {
        // Given
        long todoId = 1L;
        Todo todo = new Todo(todoId, "Task 1", "Description 1");

        when(todoService.getTodoById(todoId)).thenReturn(Optional.of(todo));

        // When
        ResponseEntity<Todo> responseEntity = todoController.getTodoById(todoId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(todo);
    }

    @Test
    void getTodoById_NotFound() {
        // Given
        long nonExistentTodoId = 99L;

        when(todoService.getTodoById(nonExistentTodoId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Todo> responseEntity = todoController.getTodoById(nonExistentTodoId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createTodo() {
        // Given
        Todo todoToCreate = new Todo(null, "New Task", "New Description");
        Todo createdTodo = new Todo(1L, "New Task", "New Description");

        when(todoService.saveTodo(any(Todo.class))).thenReturn(createdTodo);

        // When
        ResponseEntity<Todo> responseEntity = todoController.createTodo(todoToCreate);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(createdTodo);
    }

    @Test
    void updateTodo() {
        // Given
        long todoId = 1L;
        Todo existingTodo = new Todo(todoId, "Task 1", "Description 1");
        Todo updatedTodo = new Todo(todoId, "Updated Task", "Updated Description");

        when(todoService.getTodoById(todoId)).thenReturn(Optional.of(existingTodo));
        when(todoService.saveTodo(any(Todo.class))).thenReturn(updatedTodo);

        // When
        ResponseEntity<Todo> responseEntity = todoController.updateTodo(todoId, updatedTodo);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(updatedTodo);
    }

    @Test
    void updateTodo_NotFound() {
        // Given
        long nonExistentTodoId = 99L;
        Todo updatedTodo = new Todo(nonExistentTodoId, "Updated Task", "Updated Description");

        when(todoService.getTodoById(nonExistentTodoId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Todo> responseEntity = todoController.updateTodo(nonExistentTodoId, updatedTodo);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

/*    @Test
    void deleteTodo() {
        // Given
        long todoId = 1L;

        // When
        when(todoController.deleteTodo(todoId)).thenReturn(ResponseEntity.noContent().build());
        ResponseEntity<Void> voidResponseEntity = todoController.deleteTodo(todoId);
        // Then
        assertThat(voidResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(todoService, times(1)).deleteTodo(todoId);
    }*/

    @Test
    void deleteTodo_NotFound() {
        // Given
        long nonExistentTodoId = 99L;

        when(todoService.getTodoById(nonExistentTodoId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Void> responseEntity = todoController.deleteTodo(nonExistentTodoId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(todoService, never()).deleteTodo(anyLong());
    }
}
