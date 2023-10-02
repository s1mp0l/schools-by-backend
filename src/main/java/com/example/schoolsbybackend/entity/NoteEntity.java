package com.example.schoolsbybackend.entity;

import jakarta.persistence.*;

@Entity
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String text;
    private Boolean seenStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public NoteEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(Boolean seenStatus) {
        this.seenStatus = seenStatus;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
