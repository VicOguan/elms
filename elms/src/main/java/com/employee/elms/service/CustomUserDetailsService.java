package com.employee.elms.service;

import com.employee.elms.entity.AppUser;
import com.employee.elms.exception.LeaveInvalidException;
import com.employee.elms.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        AppUser appUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User"+username+" not found"
                ));
        if (appUser.getEmployee() == null){
            throw new LeaveInvalidException("User is not linked to an employee");
        }
        return User.builder()
                .username(appUser.getUserName())
                .password(appUser.getPassword())
                .roles(appUser.getRole())
                .build();
    }
}
