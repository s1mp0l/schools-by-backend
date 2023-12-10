package com.example.schoolsbybackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "nclass")
    private ClassEntity nclass;

    @ManyToMany
    private List<ParentEntity> parents;

    @JsonManagedReference
    @OneToMany(mappedBy = "student")
    private List<AbsenceEntity> absence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ClassEntity getNclass() {
        return nclass;
    }

    public void setNclass(ClassEntity nclass) {
        this.nclass = nclass;
    }

    public List<ParentEntity> getParents() {
        return parents;
    }

    public void setParents(List<ParentEntity> parents) {
        this.parents = parents;
    }
}
