package com.employee.elms.entity;

public enum EmploymentStatus {
    ACTIVE, ON_LEAVE, RESIGNED, TERMINATED;

    public boolean isInactive(){
        return this == RESIGNED || this == TERMINATED;
    }
}
