package com.bezkoder.spring.security.postgresql.services;

import com.bezkoder.spring.security.postgresql.dtos.InternshipDto;
import com.bezkoder.spring.security.postgresql.dtos.StudentDto;
import com.bezkoder.spring.security.postgresql.entities.Internship;
import com.bezkoder.spring.security.postgresql.entities.Manager;
import com.bezkoder.spring.security.postgresql.entities.Student;
import com.bezkoder.spring.security.postgresql.entities.User;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import com.bezkoder.spring.security.postgresql.mappers.DtoMapper;
import com.bezkoder.spring.security.postgresql.repository.InternshipRepository;
import com.bezkoder.spring.security.postgresql.repository.ManagerRepository;
import com.bezkoder.spring.security.postgresql.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;
    private final DtoMapper dtoMapper;

    public InternshipServiceImpl(
            InternshipRepository internshipRepository,
            ManagerRepository managerRepository,
            StudentRepository studentRepository,
            DtoMapper dtoMapper) {
        this.internshipRepository = internshipRepository;
        this.managerRepository = managerRepository;
        this.studentRepository = studentRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public ResponseEntity<InternshipDto> saveInternship(Long managerId, InternshipDto internshipDto) throws ResourceNotFoundException {
        Internship internship = dtoMapper.fromInternshipDTO(internshipDto);

        Internship savedInternship = managerRepository.findById(managerId).map(manager -> {
            internship.setManager((Manager) manager);
            return internshipRepository.save(internship);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Manager with id = " + managerId));

        return new ResponseEntity<>(dtoMapper.fromInternship(savedInternship), HttpStatus.CREATED);
    }

    @Override
    public List<InternshipDto> listInternships() {
        List<Internship> internships = internshipRepository.findAll();
        return internships.stream()
                .map(dtoMapper::fromInternship)
                .collect(Collectors.toList());
    }

    @Override
    public InternshipDto getInternship(Long internshipId) throws ResourceNotFoundException {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship Not found"));
        return dtoMapper.fromInternship(internship);
    }

    @Override
    public InternshipDto updateInternship(InternshipDto internshipDto) {
        Internship internship = dtoMapper.fromInternshipDTO(internshipDto);
        Internship savedInternship = internshipRepository.save(internship);
        return dtoMapper.fromInternship(savedInternship);
    }

    @Override
    public ResponseEntity<List<InternshipDto>> getAllInternshipByStudentId(Long studentId) throws ResourceNotFoundException {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
        }

        Optional<User> student = studentRepository.findById(studentId);

        List<Internship> internships = internshipRepository.findByStudentsId((Student) student.get());
        List<InternshipDto> internshipsDto = internships.stream()
                .map(dtoMapper::fromInternship)
                .collect(Collectors.toList());
        return new ResponseEntity<>(internshipsDto, HttpStatus.OK);
    }

    /*@Override
    public ResponseEntity<List<StudentDto>> getAllStudentsByInternshipId(Long internshipId) throws ResourceNotFoundException {
        if (!internshipRepository.existsById(internshipId)) {
            throw new ResourceNotFoundException("Not found Internship  with id = " + internshipId);
        }

        List<Student> students = studentRepository.findStudentsByInternshipsId(internshipId);
        List<StudentDto> studentDtoList = students.stream()
                .map(dtoMapper::fromStudent)
                .collect(Collectors.toList());
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }*/

    @Override
    public void deleteInternship(Long internshipId) {
        internshipRepository.deleteById(internshipId);
    }


    @Override
    public List<InternshipDto> searchInternships(String keyword) {
        List<Internship> internships = internshipRepository.searchInternship(keyword);
        return internships.stream().map(dtoMapper::fromInternship).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<List<InternshipDto>> getAllInternshipsByManagerId(Long managerId) throws ResourceNotFoundException {
        if (!managerRepository.existsById(managerId)) {
            throw new ResourceNotFoundException("Not found Manager with id = " + managerId);
        }

        List<Internship> internships = internshipRepository.findByManagerId(managerId);
        List<InternshipDto> internshipsDto = internships.stream()
                .map(dtoMapper::fromInternship)
                .collect(Collectors.toList());
        return new ResponseEntity<>(internshipsDto, HttpStatus.OK);
    }
}
