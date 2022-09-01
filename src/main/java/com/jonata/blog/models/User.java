package com.jonata.blog.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    private String about;
    @Column(nullable = false)
    private String password;

}
