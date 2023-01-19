package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.entities.Manager;
import com.bezkoder.spring.security.postgresql.entities.Role;
import com.bezkoder.spring.security.postgresql.entities.Student;
import com.bezkoder.spring.security.postgresql.entities.User;
import com.bezkoder.spring.security.postgresql.enums.DateFormatPattern;
import com.bezkoder.spring.security.postgresql.enums.ERole;
import com.bezkoder.spring.security.postgresql.exceptions.InvalidDateException;
import com.bezkoder.spring.security.postgresql.mappers.DateUtils;
import com.bezkoder.spring.security.postgresql.payload.request.LoginRequest;
import com.bezkoder.spring.security.postgresql.payload.request.ManagerSignUpRequest;
import com.bezkoder.spring.security.postgresql.payload.request.StudentSignUpRequest;
import com.bezkoder.spring.security.postgresql.payload.response.JwtResponse;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.ManagerRepository;
import com.bezkoder.spring.security.postgresql.repository.RoleRepository;
import com.bezkoder.spring.security.postgresql.repository.StudentRepository;
import com.bezkoder.spring.security.postgresql.security.jwt.JwtUtils;
import com.bezkoder.spring.security.postgresql.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String ROLE_NOT_FOUND = "Error: Role is not found.";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/student-signup")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentSignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(studentRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(studentRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Date dateOfBirth = DateUtils.parseDate(signUpRequest.getDateOfBirth(), DateFormatPattern.US_DATE_SHORT_FORMAT);
        if (dateOfBirth == null) {
            throw new InvalidDateException("The date format is not correct. Date must be of format [" +
                    DateFormatPattern.US_DATE_SHORT_FORMAT.getValue()+ "]");
        }

        // Create new student account
        User student = new Student(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                dateOfBirth,
                signUpRequest.getDegree(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_STUDENT);
        if (userRole.isPresent()) {
            roles.add(userRole.get());
        } else {
            roles.add(new Role(ERole.ROLE_STUDENT));
        }
        student.setRoles(roles);
        studentRepository.saveAndFlush(student);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/manager-signup")
    public ResponseEntity<?> registerManager(@Valid @RequestBody ManagerSignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(managerRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(managerRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new manager account
        User manager = new Manager(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getProfession(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_MANAGER);
        if (userRole.isPresent()) {
            roles.add(userRole.get());
        } else {
            roles.add(new Role(ERole.ROLE_MANAGER));
        }

        manager.setRoles(roles);
        managerRepository.save(manager);

        return ResponseEntity.ok(new MessageResponse("Manager registered successfully!"));
    }
}
