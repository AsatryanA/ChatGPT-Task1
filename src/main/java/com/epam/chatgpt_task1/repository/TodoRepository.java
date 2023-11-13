package com.epam.chatgpt_task1.repository;

import com.epam.chatgpt_task1.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
