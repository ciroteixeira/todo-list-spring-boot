package com.cmvt.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmvt.todolist.dto.TaskDto;
import com.cmvt.todolist.model.Task;
import com.cmvt.todolist.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
    @Transactional
    public List<TaskDto> findAll() {
        List<Task> list = repository.findAll();
        return list.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
    }
    
    @Transactional
    public TaskDto findById(Long id) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada! ID: " + id));
        return new TaskDto(entity);
    }
    
    @Transactional
    public TaskDto insert(TaskDto dto) {
        Task entity = new Task();
        copyDtoToEntity(dto, entity);
        entity.setCompleted(false); // Toda tarefa nova nasce aberta/não concluída
        entity = repository.save(entity);
        return new TaskDto(entity);
    }
    
    @Transactional
    public TaskDto update(Long id, TaskDto dto) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada! ID: " + id));
        copyDtoToEntity(dto, entity);
        entity.setCompleted(dto.isCompleted());
        entity = repository.save(entity);
        return new TaskDto(entity);
    }
    
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada! ID: " + id);
        }
        repository.deleteById(id);
    }
    
    // Método extra para marcar/desmarcar tarefa rapidamente
    @Transactional
    public TaskDto toggleStatus(Long id) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada! ID: " + id));
        entity.setCompleted(!entity.isCompleted()); // Inverte o booleano
        entity = repository.save(entity);
        return new TaskDto(entity);
    }
    
    // Isolando o mapeamento de campos repetitivos
    private void copyDtoToEntity(TaskDto dto, Task entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDueDate(dto.getDueDate());
    }
    
    
    
    

}
