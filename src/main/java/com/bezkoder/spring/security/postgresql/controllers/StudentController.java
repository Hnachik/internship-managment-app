package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.InternshipDto;
import com.bezkoder.spring.security.postgresql.dtos.StudentDto;
import com.bezkoder.spring.security.postgresql.dtos.StudentInternshipRequestDto;
import com.bezkoder.spring.security.postgresql.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class StudentController {
    /*private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDto> students() {
        return studentService.listStudents();
    }

    @GetMapping("/students/search")
    public List<StudentDto> searchStudents(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return studentService.searchStudents("%" + keyword + "%");
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudent(@PathVariable(name = "id") Long studentId) throws StudentNotFoundException {
        return studentService.getStudent(studentId);
    }

    @PostMapping("/students/apply-internship")
    public void applyForInternship(@RequestBody StudentInternshipRequestDto requestDto) {
        studentService.applyForInternship(
                requestDto.getStudentId(),
                requestDto.getInternshipId(),
                requestDto.getRequest());
    }

    @PostMapping("/students/{studentId}/add-report")
    public StudentDto saveStudent(@PathVariable Long studentId, @RequestBody InternshipDto internshipDto) {
        return studentService.saveStudent(studentDto);
    }

    @PostMapping("/students/{studentId}/save-assignment")
    public StudentDto saveStudent(@PathVariable Long studentId, @RequestBody InternshipDto internshipDto) {
        return studentService.saveStudent(studentDto);
    }

    @PutMapping("/students/{studentId}")
    public StudentDto updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
        studentDto.setId(studentId);
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }*/
}
