package com.employee.elms.mapper;

import com.employee.elms.dto.LeaveRequestDTO;
import com.employee.elms.dto.LeaveResponseDTO;
import com.employee.elms.entity.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {
    public LeaveRequest toEntity(LeaveRequestDTO request){
        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setLeaveType(request.getLeaveType());
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequest.setReason(request.getReason());

        return leaveRequest;
    }

    public LeaveResponseDTO toResponse(LeaveRequest leaveRequest){
        return new LeaveResponseDTO(
                leaveRequest.getId(),
                leaveRequest.getEmployee().getId(),
                leaveRequest.getEmployee().getFirstname() + " " +
                    leaveRequest.getEmployee().getLastName(),
                leaveRequest.getLeaveType(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getReason(),
                leaveRequest.getStatus(),
                leaveRequest.getCreatedAt()
        );
    }
}
