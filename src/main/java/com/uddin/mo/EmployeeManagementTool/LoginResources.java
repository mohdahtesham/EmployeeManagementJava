package com.uddin.mo.EmployeeManagementTool;

import com.uddin.mo.EmployeeManagementTool.exceptions.UserNotFoundException;
import com.uddin.mo.EmployeeManagementTool.model.Employee;
import com.uddin.mo.EmployeeManagementTool.model.Login;
import com.uddin.mo.EmployeeManagementTool.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginResources {
    private final LoginService loginService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public LoginResources(LoginService loginService) {
        this.loginService = loginService;
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<Login>> getAllUsers () {
        List<Login> users = loginService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Login> createUser(@RequestBody Login login) {
        if(loginService.exist(login.getEmail())){
            throw new UserNotFoundException("User by email " + login.getEmail() + "exists");
//            return new ResponseEntity<>(login,HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            Login newUser = loginService.createNewUser(login);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }


    @PostMapping("/signIn")
    public ResponseEntity<Login> signInUser(@RequestBody Login login) {
            Login savedPassword = loginService.findUser(login);
            if(this.encoder.matches(login.getPassword(), savedPassword.getPassword())){
                return new ResponseEntity<>(login,HttpStatus.OK);
            } else{
                return new ResponseEntity<>(login,HttpStatus.INTERNAL_SERVER_ERROR);
            }


    }


}
