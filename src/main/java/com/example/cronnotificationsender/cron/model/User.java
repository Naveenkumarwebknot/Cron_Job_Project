package com.example.cronnotificationsender.cron.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "user", schema = "public")
public class User {
    @Id
    private Long id;
    private String username;
    private String email;
    private Subscribed subscribe;
}
