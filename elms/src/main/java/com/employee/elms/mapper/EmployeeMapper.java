package com.employee.elms.mapper;

import com.employee.elms.dto.EmployeeRequest;
import com.employee.elms.dto.EmployeeResponse;
import com.employee.elms.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest request){
        Employee employee = new Employee();

        employee.setEmployeeNumber(request.getEmployeeNumber());
        employee.setFirstname(request.getFirstname());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDepartment(request.getDepartment());
        employee.setPosition(request.getPosition());
        employee.setHiredDate(request.getHiredDate());
        employee.setEmployeeStatus(request.getEmployeeStatus());

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee){
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeNumber(),
                employee.getFirstname(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getDepartment(),
                employee.getPosition(),
                employee.getHiredDate(),
                employee.getEmployeeStatus()
        );
    }
}
