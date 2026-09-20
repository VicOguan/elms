package com.employee.elms.exception;

public class LeaveInvalidException extends RuntimeException{
    public LeaveInvalidException(String message){
        super(message);
    }
}
