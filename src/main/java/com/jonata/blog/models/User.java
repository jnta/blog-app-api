package com.jonata.blog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    private String about;
    @Column(nullable = false)
    private String password;

}
