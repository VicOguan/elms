package com.employee.elms.service;

import com.employee.elms.dto.RegisterRequest;
import com.employee.elms.entity.AppUser;
import com.employee.elms.entity.Employee;
import com.employee.elms.exception.EmployeeNotFoundException;
import com.employee.elms.repository.EmployeeRepository;
import com.employee.elms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmployeeRepository employeeRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    public void register(RegisterRequest request){

        Employee employee = employeeRepository
                .findByEmployeeNumber(request.getEmployeeNumber())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found!"));

        AppUser appUser = new AppUser();
        appUser.setUserName(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setRole("EMPLOYEE");
        appUser.setEmployee(employee);

        userRepository.save(appUser);
    }

    public void registerManager(RegisterRequest request){

        Employee employee = employeeRepository
                .findByEmployeeNumber(request.getEmployeeNumber())
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found!"));

        AppUser appUser = new AppUser();
        appUser.setUserName(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setRole("MANAGER");
        appUser.setEmployee(employee);

        userRepository.save(appUser);
    }
}
