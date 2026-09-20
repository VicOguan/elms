package com.employee.elms.controller;

import com.employee.elms.dto.ApiResponse;
import com.employee.elms.dto.EmployeeRequest;
import com.employee.elms.dto.EmployeeResponse;
import com.employee.elms.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getEmployee());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int id){
        EmployeeResponse response = employeeService.getEmployeeId(id);

        if (response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/employee")
    public ResponseEntity<ApiResponse<EmployeeResponse>> addEmployee(
            @Valid @RequestBody EmployeeRequest request){

        EmployeeResponse response = employeeService.addEmployee(request);

        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>(
                "Added Successfully!",
                response);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(
            @PathVariable int id, @Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.updateEmployee(id, request);

        if (response == null){
            return ResponseEntity.notFound().build();
        }
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>("Update Successfully",
                response);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable int id){
        employeeService.deleteEmployee(id);

        ApiResponse<Void> response = new ApiResponse<>("Deleted Successfully!", null);

        return ResponseEntity.ok(response);
    }
}
