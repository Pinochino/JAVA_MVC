package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Please provide student's fullname")
    private String fullname;

    @Column(nullable = false)
    @NotBlank(message = "Please provide student's gmail")
    private String gmail;

    @Column(nullable = false)
    @NotBlank(message = "Please provide student's password")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Please provide student's numberphone")
    private String numberphone;


}