package com.employee.elms.initializer;

import com.employee.elms.entity.AppUser;
import com.employee.elms.entity.Employee;
import com.employee.elms.entity.EmploymentStatus;
import com.employee.elms.exception.EmployeeNotFoundException;
import com.employee.elms.repository.EmployeeRepository;
import com.employee.elms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        if (userRepository.findByUserName("admin").isEmpty()) {

            Employee employee = employeeRepository
                    .findByEmployeeNumber("EMP001")
                    .orElseGet(() -> {
                        Employee emp = new Employee();
                        emp.setEmployeeNumber("EMP001");
                        emp.setFirstname("System");
                        emp.setLastName("Admin");
                        emp.setEmail("SytemAdmin@email.com");
                        emp.setDepartment("IT");
                        emp.setPosition("Administrator");
                        emp.setHiredDate(LocalDate.now());
                        emp.setEmployeeStatus(EmploymentStatus.ACTIVE);
                        return employeeRepository.save(emp);
                    });
            if (userRepository.findByUserName("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole("ADMIN");
                admin.setEmployee(employee);

                userRepository.save(admin);
            }
        }
    }
}
