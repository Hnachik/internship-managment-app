package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.entities.TaskAssignment;
import com.bezkoder.spring.security.postgresql.message.ResponseFile;
import com.bezkoder.spring.security.postgresql.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class FilesController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task-assignments")
    public ResponseEntity<List<ResponseFile>> getListTaskAssignments() {
        List<ResponseFile> files = taskService.getAllAssignments().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/task-assignments/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getTitle(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getDocument().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/task-assignment-document/{id}")
    public ResponseEntity<byte[]> getTaskAssignmentDocument(@PathVariable String id) {
        TaskAssignment taskAssignment = taskService.getAssignment(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + taskAssignment.getTitle() + "\"")
                .body(taskAssignment.getDocument());
    }
}
