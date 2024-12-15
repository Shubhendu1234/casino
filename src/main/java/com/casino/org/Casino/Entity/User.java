package com.casino.org.Casino.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer userId;

    private String userName;

    private Double balance;



}
