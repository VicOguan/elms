package com.employee.elms.dto;

import com.employee.elms.entity.EmploymentStatus;
import com.employee.elms.entity.LeaveType;

import java.time.LocalDate;

public class LeaveRequestDTO {
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private EmploymentStatus employeeStatus;

    public LeaveRequestDTO(){}

    public LeaveType getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public EmploymentStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmploymentStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
