package com.cmvt.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmvt.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	// Herdando o CRUD completo automaticamente

}
