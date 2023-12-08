package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.NoteEntity;

public class Note {
    private Long id;
    private String title;
    private String text;
    private Boolean seenStatus;
    private Long user;

    public static Note toModel(NoteEntity entity) {
        Note model = new Note();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setText(entity.getText());
        model.setSeenStatus(entity.getSeenStatus());
        model.setUser(entity.getUser().getId());
        return model;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
