package com.alphacoder.springsecurityoauth2.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "USER")
@Data
public class UserEntity {
    @Id
    @Column(name="USERNAME")
    private String username;

    @Column(name= "PASSWORD")
    private String password;
}
