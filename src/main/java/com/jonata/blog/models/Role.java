package com.jonata.blog.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    private Integer id;

    private String name;
}
