package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {
    }

    public Schedule(String author, String title, String contents, User user) {
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void updateSchedule(String author, String title, String contents) {
        this.author = author;
        this.title = title;
        this.contents = contents;
    }
}
