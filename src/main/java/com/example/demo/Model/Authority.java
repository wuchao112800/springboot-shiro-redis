package com.example.demo.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String function_name;


}
