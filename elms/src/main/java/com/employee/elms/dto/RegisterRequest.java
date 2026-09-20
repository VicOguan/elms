package com.employee.elms.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String employeeNumber;

    public RegisterRequest(){}

    public RegisterRequest(String username, String password, String employeeNumber){
        this.username = username;
        this.password = password;
        this.employeeNumber = employeeNumber;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
