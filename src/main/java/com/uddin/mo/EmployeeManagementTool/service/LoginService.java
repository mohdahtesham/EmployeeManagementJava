package com.uddin.mo.EmployeeManagementTool.service;

import com.uddin.mo.EmployeeManagementTool.exceptions.UserNotFoundException;
import com.uddin.mo.EmployeeManagementTool.model.Employee;
import com.uddin.mo.EmployeeManagementTool.model.Login;
import com.uddin.mo.EmployeeManagementTool.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LoginService {
     final LoginRepo loginRepo;
     PasswordEncoder passwordEncoder;


    @Autowired
    public LoginService(LoginRepo loginRepo){

        this.loginRepo = loginRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public Login createNewUser(Login login) {
        String encodedPassword  = this.passwordEncoder.encode(login.getPassword());
        login.setPassword(encodedPassword);
        return loginRepo.save(login);
    }
    public Login findUser(Login login) {
        return loginRepo.findUserByEmail(login.getEmail()).orElseThrow(() -> new UserNotFoundException("User by email " + login.getEmail() + " was not found"));

    }

    public boolean exist(String email){
        return loginRepo.existsByEmail(email);
    }
    public List<Login> findAllUsers() {
        return loginRepo.findAll();
    }


}
