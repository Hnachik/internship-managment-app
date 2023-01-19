package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.InternshipDto;
import com.bezkoder.spring.security.postgresql.dtos.StudentDto;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import com.bezkoder.spring.security.postgresql.services.InternshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class InternshipController {
    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping("/internships")
    public List<InternshipDto> internships() {
        return internshipService.listInternships();
    }

    @GetMapping("/internships/search")
    public List<InternshipDto> searchInternships(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return internshipService.searchInternships("%" + keyword + "%");
    }

    @GetMapping("/internships/{id}")
    public InternshipDto getInternship(@PathVariable(name = "id") Long internshipId) throws ResourceNotFoundException {
        return internshipService.getInternship(internshipId);
    }

    @PutMapping("/internships/{internshipId}")
    public InternshipDto updateInternship(@PathVariable Long internshipId, @RequestBody InternshipDto internshipDto) {
        internshipDto.setId(internshipId);
        return internshipService.updateInternship(internshipDto);
    }

    @DeleteMapping("/internships/{id}")
    public void deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
    }

    @GetMapping("/managers/{managerId}/internships")
    public ResponseEntity<List<InternshipDto>> getAllInternshipsByManagerId(@PathVariable Long managerId) throws ResourceNotFoundException {
        return internshipService.getAllInternshipsByManagerId(managerId);
    }

    @PostMapping("/managers/{managerId}/post-internship")
    public ResponseEntity<InternshipDto> createInternship(@PathVariable Long managerId,
                                                 @RequestBody InternshipDto internshipDto) throws ResourceNotFoundException {
        return internshipService.saveInternship(managerId, internshipDto);
    }

    @GetMapping("/students/{studentId}/internships")
    public ResponseEntity<List<InternshipDto>> getAllInternshipByStudentId(@PathVariable(value = "studentId") Long studentId) throws ResourceNotFoundException {
        return internshipService.getAllInternshipByStudentId(studentId);
    }

    /*@GetMapping("/internships/{id}/students")
    public ResponseEntity<List<StudentDto>> getAllStudentsByInternshipId(@PathVariable Long id) throws ResourceNotFoundException {
        return internshipService.getAllStudentsByInternshipId(id);
    }*/
}
