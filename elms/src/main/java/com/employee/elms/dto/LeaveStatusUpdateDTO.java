package com.employee.elms.dto;

import com.employee.elms.entity.LeaveStatus;
import jakarta.validation.constraints.NotNull;

public class LeaveStatusUpdateDTO {

    @NotNull(message = "Status required")
    private LeaveStatus leaveStatus;

    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
