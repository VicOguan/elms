package com.employee.elms.initializer;

import com.employee.elms.entity.AppUser;
import com.employee.elms.entity.Employee;
import com.employee.elms.exception.EmployeeNotFoundException;
import com.employee.elms.repository.EmployeeRepository;
import com.employee.elms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           EmployeeRepository employeeRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args){
        if (userRepository.findByUserName("admin").isEmpty()){

            Employee employee = employeeRepository
                    .findByEmployeeNumber("EMP001")
                    .orElseThrow(() -> new EmployeeNotFoundException("Admin emp not found!"));

            AppUser admin = new AppUser();
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRole("ADMIN");
            admin.setEmployee(employee);

            userRepository.save(admin);

            System.out.println("initial admin account register");
        }
    }
}
