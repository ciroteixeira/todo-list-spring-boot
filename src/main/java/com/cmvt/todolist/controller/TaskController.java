package com.cmvt.todolist.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cmvt.todolist.dto.TaskDto;
import com.cmvt.todolist.service.TaskService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins="*")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
    // GET: http://localhost:8080/tasks
    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        List<TaskDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    // GET por ID: http://localhost:8080/tasks/1
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) {
        TaskDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    
    // POST: http://localhost:8080/tasks
    @PostMapping
    public ResponseEntity<TaskDto> insert(@RequestBody TaskDto dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    // PUT: http://localhost:8080/tasks/1 (Atualização completa)
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody TaskDto dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    
    // PATCH: http://localhost:8080/tasks/1/toggle (Atualização parcial do status)
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TaskDto> toggleStatus(@PathVariable Long id) {
        TaskDto dto = service.toggleStatus(id);
        return ResponseEntity.ok().body(dto);
    }
    
    // DELETE: http://localhost:8080/tasks/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
