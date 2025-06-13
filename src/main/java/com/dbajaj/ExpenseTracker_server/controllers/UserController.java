package com.dbajaj.ExpenseTracker_server.controllers;

import com.dbajaj.ExpenseTracker_server.DTO.AuthRequest;
import com.dbajaj.ExpenseTracker_server.DTO.AuthResponse;
import com.dbajaj.ExpenseTracker_server.DTO.UserDTO;
import com.dbajaj.ExpenseTracker_server.entity.UserEntity;
import com.dbajaj.ExpenseTracker_server.security.JwtUtil;
import com.dbajaj.ExpenseTracker_server.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
        try{
            UserEntity userEntity = userService.registerNewUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully: " +
                    userEntity.getEmail());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest)throws Exception{
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials passed");
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt= jwtUtil.generateToken(userDetails);
        UserEntity user=userService.getByEmail(authRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(jwt,user.getId(),user.getEmail()));
    }
}
