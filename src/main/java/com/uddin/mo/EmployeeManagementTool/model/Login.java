package com.uddin.mo.EmployeeManagementTool.model;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
public class Login implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
}
