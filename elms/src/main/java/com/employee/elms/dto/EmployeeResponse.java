package com.employee.elms.dto;

import com.employee.elms.entity.EmploymentStatus;

import java.time.LocalDate;

public class EmployeeResponse {
    private long id;
    private String employeeNumber;
    private String firstname;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;
    private LocalDate hiredDate;
    private EmploymentStatus employeeStatus;

    public EmployeeResponse(long id, String employeeNumber, String firstname, String lastName,
                           String email, String phoneNumber, String department, String position,
                           LocalDate hiredDate, EmploymentStatus employeeStatus){
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
        this.hiredDate = hiredDate;
        this.employeeStatus = employeeStatus;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }
    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    public EmploymentStatus getEmployeeStatus() {
        return employeeStatus;
    }
    public void setEmployeeStatus(EmploymentStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
