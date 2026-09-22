package com.employee.elms.repository;

import com.employee.elms.entity.Employee;
import com.employee.elms.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee_Id(long id);

    boolean existsByEmployeeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Employee employeeId,
                                                                                LocalDate startDate,
                                                                                LocalDate endDate);
}
