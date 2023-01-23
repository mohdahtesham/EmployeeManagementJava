package com.uddin.mo.EmployeeManagementTool.repo;

import com.uddin.mo.EmployeeManagementTool.model.Employee;
import com.uddin.mo.EmployeeManagementTool.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login, Long> {
    boolean existsByEmail(String email);
    Optional<Login> findUserByEmail(String email);

}
