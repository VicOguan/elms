package com.employee.elms.controller;

import com.employee.elms.dto.ApiResponse;
import com.employee.elms.dto.LeaveRequestDTO;
import com.employee.elms.dto.LeaveResponseDTO;
import com.employee.elms.dto.LeaveStatusUpdateDTO;
import com.employee.elms.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveRequestController {
    private final LeaveService leaveService;

    public LeaveRequestController(LeaveService leaveService){
        this.leaveService = leaveService;
    }

    @PostMapping("/leave")
    public ResponseEntity<ApiResponse<LeaveResponseDTO>> addLeave(
            @Valid @RequestBody LeaveRequestDTO request,Authentication authentication){

        LeaveResponseDTO responseDTO = leaveService.addLeave(request, authentication.getName());

        ApiResponse<LeaveResponseDTO> apiResponse = new ApiResponse<>(
                "Added Successfully",
                responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @GetMapping("/leave")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<LeaveResponseDTO>> getAllLeaves(){

        List<LeaveResponseDTO> responseDTO = leaveService.getAllLeave();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/leave/my")
    public ResponseEntity<List<LeaveResponseDTO>> getMyLeave(
            Authentication authentication){
        System.out.println(authentication);
        System.out.println(authentication.getAuthorities());

    return ResponseEntity.ok(leaveService.getMyLeave(authentication.getName()));
    }

    @PutMapping("/leave/{id}/status")
    public ResponseEntity<ApiResponse<LeaveResponseDTO>> updateLeave(
            @PathVariable long id, @Valid @RequestBody LeaveStatusUpdateDTO requestDTO){
        LeaveResponseDTO responseDTO = leaveService.updateLeave(id, requestDTO);

        ApiResponse<LeaveResponseDTO> apiResponse = new ApiResponse<>(
                "Update Successfully!",
                responseDTO);

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/leave/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLeave(@PathVariable long id){
        leaveService.deleteLeave(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "Deleted Successfully!",
                null
        );
        return ResponseEntity.ok(response);
    }



}
