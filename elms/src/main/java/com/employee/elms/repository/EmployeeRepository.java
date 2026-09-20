package com.employee.elms.repository;

import com.employee.elms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee id(long id);
    Optional<Employee> findTopByOrderByIdDesc();

    Optional<Employee> findByEmployeeNumber(String employeeNumber);
}
