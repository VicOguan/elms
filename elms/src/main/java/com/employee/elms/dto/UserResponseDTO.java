package com.employee.elms.dto;

public class UserResponseDTO {
    private String username;
    private String role;
    private String employeeNumber;

    public UserResponseDTO(String username, String role, String employeeNumber){
        this.username = username;
        this.role = role;
        this.employeeNumber = employeeNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
