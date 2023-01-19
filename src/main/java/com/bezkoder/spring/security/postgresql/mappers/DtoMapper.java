package com.bezkoder.spring.security.postgresql.mappers;

import com.bezkoder.spring.security.postgresql.dtos.*;
import com.bezkoder.spring.security.postgresql.entities.*;
import com.bezkoder.spring.security.postgresql.enums.DateFormatPattern;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DtoMapper {

    public StudentDto fromStudent(Student student) {
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }

    public Student fromStudentDTO(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        return student;
    }

    public TaskDto fromTask(Task task) {
        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(task, taskDto);
        taskDto.setInternshipDto(fromInternship(task.getInternship()));
        return taskDto;
    }

    public Task fromTaskDTO(TaskDto taskDto) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task);
        task.setInternship(fromInternshipDTO(taskDto.getInternshipDto()));
        return task;
    }

    public InternshipDto fromInternship(Internship internship) {
        InternshipDto internshipDto = new InternshipDto();

        internshipDto.setId(internship.getId());
        internshipDto.setTitle(internship.getTitle());
        internshipDto.setDescription(internship.getDescription());
        internshipDto.setTheme(internship.getTheme());

        String startDate = DateUtils.formatDate(internship.getStartDate(), DateFormatPattern.US_DATE_SHORT_FORMAT);
        String endDate = DateUtils.formatDate(internship.getEndDate(), DateFormatPattern.US_DATE_SHORT_FORMAT);
        internshipDto.setStartDate(startDate);
        internshipDto.setEndDate(endDate);
        BeanUtils.copyProperties(internship, internshipDto);
        //internshipDto.setStudentDto(fromStudent(internship.getStudent()));
        internshipDto.setManagerDto(fromManager(internship.getManager()));
        return internshipDto;
    }

    public Internship fromInternshipDTO(InternshipDto internshipDto) {
        Internship internship = new Internship();
        internship.setId(internshipDto.getId());
        internship.setTitle(internshipDto.getTitle());
        internship.setDescription(internshipDto.getDescription());
        internship.setTheme(internshipDto.getTheme());
        Date startDate = DateUtils.parseDate(internshipDto.getStartDate(), DateFormatPattern.US_DATE_SHORT_FORMAT);
        Date endDate = DateUtils.parseDate(internshipDto.getEndDate(), DateFormatPattern.US_DATE_SHORT_FORMAT);
        internship.setStartDate(startDate);
        internship.setEndDate(endDate);
        //internship.setStudent(fromStudentDTO((internshipDto.getStudentDto())));
        //internship.setManager(fromManagerDTO(internshipDto.getManagerDto()));
        return internship;
    }

    public ManagerDto fromManager(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        BeanUtils.copyProperties(manager, managerDto);
        return managerDto;
    }

    public Manager fromManagerDTO(ManagerDto managerDto) {
        Manager manager = new Manager();
        BeanUtils.copyProperties(managerDto, manager);
        return manager;
    }

    public CompanyDto fromCompany(Company company) {
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);
        return companyDto;
    }

    public Company fromCompanyDTO(CompanyDto companyDto) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        return company;
    }

    private TaskAssignmentDto fromTaskAssignment(TaskAssignment taskAssignment) {
        TaskAssignmentDto taskAssignmentDto = new TaskAssignmentDto();
        BeanUtils.copyProperties(taskAssignment, taskAssignmentDto);
        taskAssignmentDto.setStudentDto(fromStudent(taskAssignment.getStudent()));
        taskAssignmentDto.setTaskDto(fromTask(taskAssignment.getTask()));

        return taskAssignmentDto;
    }

    private TaskAssignment fromTaskAssignmentDTO(TaskAssignmentDto taskAssignmentDto) {
        TaskAssignment taskAssignment = new TaskAssignment();
        BeanUtils.copyProperties(taskAssignmentDto, taskAssignment);
        taskAssignment.setStudent(fromStudentDTO((taskAssignmentDto.getStudentDto())));
        taskAssignment.setTask(fromTaskDTO((taskAssignmentDto.getTaskDto())));

        return taskAssignment;
    }
}
