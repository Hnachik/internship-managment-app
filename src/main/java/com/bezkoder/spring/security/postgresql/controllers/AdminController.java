package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.StudentRequestDto;
import com.bezkoder.spring.security.postgresql.entities.StudentRequest;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import com.bezkoder.spring.security.postgresql.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/student-requests")
    public List<StudentRequest> internships() {
        return adminService.getAllStudentRequests();
    }

    @GetMapping("/internship/{internshipId}/student-requests")
    public ResponseEntity<List<StudentRequest>> getAllStudentRequestsByInternshipId(@PathVariable Long internshipId) throws ResourceNotFoundException {
        return adminService.getAllStudentRequestsByInternshipId(internshipId);
    }

    @GetMapping("/students/{studentId}/student-requests")
    public ResponseEntity<List<StudentRequest>> getAllStudentRequestsByStudentId(@PathVariable Long studentId) throws ResourceNotFoundException {
        return adminService.getAllStudentRequestsByStudentId(studentId);
    }

    @PostMapping("/student/{studentId}/add-student-request")
    public ResponseEntity<StudentRequest> saveStudentRequest(@PathVariable Long studentId,
                                                           @RequestBody StudentRequestDto studentRequestDto) throws ResourceNotFoundException {
        return adminService.saveStudentRequest(studentId, studentRequestDto);
    }

    @PostMapping("/admin/approve-request")
    public ResponseEntity<StudentRequest> saveStudentRequest(@RequestBody StudentRequestDto studentRequestDto) throws ResourceNotFoundException {
        return adminService.approveStudentRequest(studentRequestDto);
    }

    @PostMapping("/admin/reject-request")
    public ResponseEntity<StudentRequest> rejectStudentRequest(@RequestBody StudentRequestDto studentRequestDto) throws ResourceNotFoundException {
        return adminService.rejectStudentRequest(studentRequestDto);
    }
}
