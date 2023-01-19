package com.bezkoder.spring.security.postgresql.dtos;

public class InternshipDto {
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private String description;
    private String theme;
    private ManagerDto managerDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public ManagerDto getManagerDto() {
        return managerDto;
    }

    public void setManagerDto(ManagerDto managerDto) {
        this.managerDto = managerDto;
    }
}
