package com.employee.elms.service;

import com.employee.elms.dto.EmployeeRequest;
import com.employee.elms.dto.EmployeeResponse;
import com.employee.elms.entity.Employee;
import com.employee.elms.exception.EmployeeNotFoundException;
import com.employee.elms.mapper.EmployeeMapper;
import com.employee.elms.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeMapper employeeMapper){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    //Get all employee
    public List<EmployeeResponse> getEmployee(){
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    //Get employee by ID
    public EmployeeResponse getEmployeeId(long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id "+id+"is not found!"));

        return employeeMapper.toResponse(employee);
    }

    //Add new Employee
    public EmployeeResponse addEmployee(EmployeeRequest request){
        Employee employee = employeeMapper.toEntity(request);

        String employeeNumber = employeeRepository.findTopByOrderByIdDesc()
                .map(e -> {
                    long next = e.getId() + 1;
                    return String.format("EMP%03d", next);
                }).orElse("EMP001");

        employee.setEmployeeNumber(employeeNumber);

        Employee saveEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(saveEmployee);
    }

    //Update Employee
    @Transactional
    public EmployeeResponse updateEmployee(long id, EmployeeRequest request){
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id "+id+"is not found!"));

        employee.setFirstname(request.getFirstname());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDepartment(request.getDepartment());
        employee.setPosition(request.getPosition());
        employee.setHiredDate(request.getHiredDate());
        employee.setEmployeeStatus(request.getEmployeeStatus());

        Employee updateEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(updateEmployee);
    }

    //Delete employee
    @Transactional
    public void deleteEmployee(long id){
        if (!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Employee with id "+id+"is not found!");
        }

        employeeRepository.deleteById(id);
    }

}
