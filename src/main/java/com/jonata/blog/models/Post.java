package com.jonata.blog.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "post_title", nullable = false, length = 100)
    private String title;
    @Column(length = 10000)
    private String content;
    private String imageName;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

}
