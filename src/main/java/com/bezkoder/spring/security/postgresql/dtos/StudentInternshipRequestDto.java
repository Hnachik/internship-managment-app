package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.enums.EStudentRequest;

public class StudentInternshipRequestDto {
    private StudentDto studentDto;
    private InternshipDto internshipDto;
    private EStudentRequest request;

    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    public InternshipDto getInternshipDto() {
        return internshipDto;
    }

    public void setInternshipDto(InternshipDto internshipDto) {
        this.internshipDto = internshipDto;
    }

    public EStudentRequest getRequest() {
        return request;
    }

    public void setRequest(EStudentRequest request) {
        this.request = request;
    }
}
