package com.employee.elms.controller;

import com.employee.elms.dto.UserResponseDTO;
import com.employee.elms.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> userResponseDTOS = userRepository.findAll().stream()
                .filter(u -> u.getEmployee() != null)
                .map(u -> new UserResponseDTO(
                        u.getUserName(),
                        u.getRole(),
                        u.getEmployee().getEmployeeNumber()))
                .toList();
        return ResponseEntity.ok(userResponseDTOS);
    }

}
