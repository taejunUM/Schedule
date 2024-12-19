package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String pw;

    public User() {
    }

    public User(String userName, String email, String pw) {
        this.userName = userName;
        this.email = email;
        this.pw = pw;
    }

    public void updateUser(String userName, String email, String pw) {
        this.userName = userName;
        this.email = email;
        this.pw = pw;
    }





}
