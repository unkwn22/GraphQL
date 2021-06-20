package com.example.testproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}
