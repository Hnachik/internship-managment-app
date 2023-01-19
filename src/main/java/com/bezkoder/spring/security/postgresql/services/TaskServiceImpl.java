package com.bezkoder.spring.security.postgresql.services;

import com.bezkoder.spring.security.postgresql.dtos.TaskAssignmentDto;
import com.bezkoder.spring.security.postgresql.dtos.TaskDto;
import com.bezkoder.spring.security.postgresql.entities.Task;
import com.bezkoder.spring.security.postgresql.entities.TaskAssignment;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import com.bezkoder.spring.security.postgresql.mappers.DtoMapper;
import com.bezkoder.spring.security.postgresql.repository.InternshipRepository;
import com.bezkoder.spring.security.postgresql.repository.TaskAssignmentRepository;
import com.bezkoder.spring.security.postgresql.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final InternshipRepository internshipRepository;
    private final TaskRepository taskRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final DtoMapper dtoMapper;

    public TaskServiceImpl(
            InternshipRepository internshipRepository,
            TaskRepository taskRepository,
            TaskAssignmentRepository taskAssignmentRepository,
            DtoMapper dtoMapper) {
        this.internshipRepository = internshipRepository;
        this.taskRepository = taskRepository;
        this.taskAssignmentRepository = taskAssignmentRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<TaskDto> searchTasks(String keyword) {
        List<Task> tasks = taskRepository.searchTask(keyword);
        return tasks.stream().map(dtoMapper::fromTask).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(Long id) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship Not found"));
        return dtoMapper.fromTask(task);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        Task task = dtoMapper.fromTaskDTO(taskDto);
        Task savedTask = taskRepository.save(task);
        return dtoMapper.fromTask(savedTask);
    }

    @Override
    public void deleteTask(Long id) {
        internshipRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getAllTasksByInternshipId(Long id) throws ResourceNotFoundException {
        if (!internshipRepository.existsById(id)) {
            throw new ResourceNotFoundException("Not found Internship with id = " + id);
        }

        List<Task> tasks = taskRepository.findByInternshipId(id);
        List<TaskDto> taskDtoList = tasks.stream()
                .map(dtoMapper::fromTask)
                .collect(Collectors.toList());
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDto> createTask(Long id, TaskDto taskDto) throws ResourceNotFoundException {
        Task task = dtoMapper.fromTaskDTO(taskDto);

        Task savedTask = internshipRepository.findById(id).map(internship -> {
            task.setInternship(internship);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Internship with id = " + id));

        return new ResponseEntity<>(dtoMapper.fromTask(savedTask), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TaskAssignment> createTaskAssignment(Long id, TaskAssignmentDto assignmentDto) throws IOException {
        MultipartFile document = assignmentDto.getDocument();
        String documentName = StringUtils.cleanPath(Objects.requireNonNull(document.getOriginalFilename()));

        TaskAssignment assignment = new TaskAssignment(documentName, document.getContentType(), assignmentDto.getStatus(), document.getBytes());

        return new ResponseEntity<>(taskAssignmentRepository.save(assignment), HttpStatus.CREATED);
    }

    public TaskAssignment getAssignment(String id) {
        return taskAssignmentRepository.findById(id).get();
    }

    public Stream<TaskAssignment> getAllAssignments() {
        return taskAssignmentRepository.findAll().stream();
    }
}
