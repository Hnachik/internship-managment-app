package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.TaskAssignmentDto;
import com.bezkoder.spring.security.postgresql.dtos.TaskDto;
import com.bezkoder.spring.security.postgresql.entities.TaskAssignment;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import com.bezkoder.spring.security.postgresql.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    public TaskController(
            TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/search")
    public List<TaskDto> searchTasks(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return taskService.searchTasks("%" + keyword + "%");
    }

    @GetMapping("/task/{id}")
    public TaskDto getTask(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return taskService.getTask(id);
    }

    @PutMapping("/task/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return taskService.updateTask(taskDto);
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/internships/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasksByInternshipId(@PathVariable Long id) throws ResourceNotFoundException {
        return taskService.getAllTasksByInternshipId(id);
    }

    @PostMapping("/internships/{id}/add-task")
    public ResponseEntity<TaskDto> createTask(
            @PathVariable Long id,
            @RequestBody TaskDto taskDto) throws ResourceNotFoundException {
        return taskService.createTask(id, taskDto);
    }

    @PostMapping("students/{studentId}/add-task-assignment")
    public ResponseEntity<TaskAssignment> createTaskAssignment(
            @PathVariable Long studentId,
            @RequestBody TaskAssignmentDto taskAssignmentDto) throws ResourceNotFoundException, IOException {
        return taskService.createTaskAssignment(studentId, taskAssignmentDto);
    }

    @PutMapping("update-task-assignment/{id}")
    public ResponseEntity<TaskAssignment> updateTaskAssignment(
            @PathVariable String id,
            @RequestBody TaskAssignmentDto taskAssignmentDto) throws ResourceNotFoundException, IOException {
        taskAssignmentDto.setId(id);
        return taskService.updateTaskAssignment(id, taskAssignmentDto);
    }
}
