package com.employee.elms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String employeeNumber;

    private String firstname;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;
    private LocalDate hiredDate;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employeeStatus;

    public Employee(){}

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
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
