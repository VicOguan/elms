package com.employee.elms.service;

import com.employee.elms.dto.LeaveRequestDTO;
import com.employee.elms.dto.LeaveResponseDTO;
import com.employee.elms.dto.LeaveStatusUpdateDTO;
import com.employee.elms.entity.*;
import com.employee.elms.exception.LeaveInvalidException;
import com.employee.elms.mapper.LeaveMapper;
import com.employee.elms.repository.EmployeeRepository;
import com.employee.elms.repository.LeaveRepository;
import com.employee.elms.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;
    private final UserRepository userRepository;

    public LeaveService(LeaveRepository leaveRepository, LeaveMapper leaveMapper, EmployeeRepository employeeRepository, UserRepository userRepository){
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.leaveMapper = leaveMapper;
        this.userRepository = userRepository;
    }

    //Get all Leaves
    public List<LeaveResponseDTO> getAllLeave(){
        return leaveRepository
                .findAll()
                .stream()
                .map(leaveMapper::toResponse)
                .toList();
    }

    //Get currently user leaves
    public List<LeaveResponseDTO> getMyLeave(String username){

        AppUser user = userRepository.findByUserName(username)
                .orElseThrow(() -> new LeaveInvalidException("User not found!"));

        long employeeId = user.getEmployee().getId();
        return leaveRepository
                .findByEmployee_Id(employeeId)
                .stream()
                .map(leaveMapper::toResponse)
                .toList();
    }

    //Submit a leave
    @Transactional
    public LeaveResponseDTO addLeave(LeaveRequestDTO request){

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new LeaveInvalidException("Employee not found"));

        if (request.getStartDate().isAfter(request.getEndDate())){
            throw new LeaveInvalidException("Start date cannot be after end date!");
        }
        if (request.getStartDate().isBefore(LocalDate.now())){
            throw new LeaveInvalidException("Start date must be present!");
        }
        boolean hasOverlap = leaveRepository.existsByEmployeeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                employee,
                request.getStartDate(),
                request.getEndDate()
        );
        if (hasOverlap){
            throw new LeaveInvalidException("You have existing leave");
        }

        LeaveType newType = request.getLeaveType();
        if (newType == null){
            throw  new LeaveInvalidException("Cannot be null");
        }

        LeaveRequest leaveRequest = leaveMapper.toEntity(request);

        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(LeaveStatus.PENDING);

        LeaveRequest submitLeave = leaveRepository.save(leaveRequest);

        return leaveMapper.toResponse(submitLeave);
    }

    //Manager can approve and reject the leave request
    @Transactional
    public LeaveResponseDTO updateLeave(long id, LeaveStatusUpdateDTO request){
        LeaveRequest leaveRequest = leaveRepository
                .findById(id)
                .orElseThrow(() -> new LeaveInvalidException("Employee leave with id"+id+"does not exist!"));

         if (request.getLeaveStatus() == null){
             throw new LeaveInvalidException("Status cannot be null");
         }
         if (request.getLeaveStatus() == LeaveStatus.PENDING){
             throw new LeaveInvalidException("Cannot set the status back to PENDING!");
         }
        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new LeaveInvalidException("Only pending leave requests can be updated.");
        }
         if (leaveRequest.getStatus() == LeaveStatus.APPROVED){
             throw new LeaveInvalidException("Cannot modify approved leaves");
         }
         leaveRequest.setStatus(request.getLeaveStatus());
         return leaveMapper.toResponse(leaveRepository.save(leaveRequest));
    }

    public void DeleteLeave(long id){
       if (!leaveRepository.existsById(id)){
           throw new LeaveInvalidException("Employee leave with id"+id+"does not exist!");
       }
    }

}
