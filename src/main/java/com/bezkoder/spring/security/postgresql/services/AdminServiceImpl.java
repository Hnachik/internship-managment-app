package com.bezkoder.spring.security.postgresql.services;

import com.bezkoder.spring.security.postgresql.dtos.StudentRequestDto;
import com.bezkoder.spring.security.postgresql.entities.Internship;
import com.bezkoder.spring.security.postgresql.entities.Student;
import com.bezkoder.spring.security.postgresql.entities.StudentRequest;
import com.bezkoder.spring.security.postgresql.enums.EStudentRequest;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import com.bezkoder.spring.security.postgresql.mappers.DtoMapper;
import com.bezkoder.spring.security.postgresql.repository.InternshipRepository;
import com.bezkoder.spring.security.postgresql.repository.StudentRepository;
import com.bezkoder.spring.security.postgresql.repository.StudentRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final InternshipRepository internshipRepository;
    private final StudentRequestRepository studentRequestRepository;
    private final StudentRepository studentRepository;
    private final DtoMapper dtoMapper;

    public AdminServiceImpl(
            InternshipRepository internshipRepository,
            StudentRequestRepository studentRequestRepository,
            StudentRepository studentRepository,
            DtoMapper dtoMapper) {
        this.internshipRepository = internshipRepository;
        this.studentRequestRepository = studentRequestRepository;
        this.studentRepository = studentRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public ResponseEntity<StudentRequest> saveStudentRequest(Long studentId, StudentRequestDto studentRequestDto) throws ResourceNotFoundException {
        validateStudentRequest(studentId, studentRequestDto.getInternshipId());

        Student student = (Student) studentRepository.findById(studentId).get();
        Internship internship = internshipRepository.findById(studentRequestDto.getInternshipId()).get();

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setInternship(internship);
        studentRequest.setStudent(student);
        studentRequest.setRequestStatus(EStudentRequest.WAITING);
        studentRequest.setRequestComment(studentRequestDto.getComment());

        return new ResponseEntity<>(studentRequestRepository.save(studentRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<StudentRequest>> getAllStudentRequestsByStudentId(Long studentId) throws ResourceNotFoundException {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
        }
        List<StudentRequest> studentRequests = studentRequestRepository.findByStudentId(studentId);
        return new ResponseEntity<>(studentRequests, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StudentRequest>> getAllStudentRequestsByInternshipId(Long internshipId) throws ResourceNotFoundException {
        if (!internshipRepository.existsById(internshipId)) {
            throw new ResourceNotFoundException("Not found Internship with id = " + internshipId);
        }
        List<StudentRequest> studentRequests = studentRequestRepository.findByInternshipId(internshipId);
        return new ResponseEntity<>(studentRequests, HttpStatus.OK);
    }

    @Override
    public List<StudentRequest> getAllStudentRequests() {
        return studentRequestRepository.findAll();
    }

    @Override
    public ResponseEntity<StudentRequest> approveStudentRequest(StudentRequestDto studentRequestDto) throws ResourceNotFoundException {
        validateStudentRequest(studentRequestDto.getStudentId(), studentRequestDto.getInternshipId());
        Student student = (Student) studentRepository.findById(studentRequestDto.getStudentId()).get();
        Internship internship = internshipRepository.findById(studentRequestDto.getInternshipId()).get();
        StudentRequest studentRequest = studentRequestRepository.findById(studentRequestDto.getStudentId()).get();
        student.addInternship(internship);
        studentRepository.save(student);
        studentRequest.setRequestStatus(EStudentRequest.APPROVED);

        return new ResponseEntity<>(studentRequestRepository.save(studentRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentRequest> rejectStudentRequest(StudentRequestDto studentRequestDto) throws ResourceNotFoundException {
        validateStudentRequest(studentRequestDto.getStudentId(), studentRequestDto.getInternshipId());
        Student student = (Student) studentRepository.findById(studentRequestDto.getStudentId()).get();
        Internship internship = internshipRepository.findById(studentRequestDto.getInternshipId()).get();
        StudentRequest studentRequest = studentRequestRepository.findById(studentRequestDto.getStudentId()).get();
        student.addInternship(internship);
        studentRepository.save(student);
        studentRequest.setRequestStatus(EStudentRequest.REJECTED);

        return new ResponseEntity<>(studentRequestRepository.save(studentRequest), HttpStatus.OK);
    }

    private void validateStudentRequest(Long studentId, Long internshipId) throws ResourceNotFoundException {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
        }

        if (!internshipRepository.existsById(internshipId)) {
            throw new ResourceNotFoundException("Not found Internship with id = " + internshipId);
        }
    }
}
