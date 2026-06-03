package com.cmvt.todolist.dto;

import java.time.LocalDate;

import com.cmvt.todolist.model.Task;

public class TaskDto {
	
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    
    public TaskDto() {
    	
    }
    
    public TaskDto(Task entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.completed = entity.isCompleted();
        this.dueDate = entity.getDueDate();
    }

	public TaskDto(Long id, String title, String description, boolean completed, LocalDate dueDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.completed = completed;
		this.dueDate = dueDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
    
    

}
