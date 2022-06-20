package com.example.springidentity2022.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.springidentity2022.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private boolean isActive;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    public User() {};

    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    @PrePersist
    @PreUpdate
    public void beforeSave() {
        if(this.createdAt == null) {
            this.createdAt = new Timestamp(System.currentTimeMillis());
        }
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
