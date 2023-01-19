package com.bezkoder.spring.security.postgresql.dtos;

public class TaskDto {
    private Long id;
    private String taskName;
    private String description;
    private InternshipDto internshipDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InternshipDto getInternshipDto() {
        return internshipDto;
    }

    public void setInternshipDto(InternshipDto internshipDto) {
        this.internshipDto = internshipDto;
    }
}
