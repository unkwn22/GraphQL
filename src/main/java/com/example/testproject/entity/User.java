package com.example.testproject.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public void update(String email, String password){
        if(!email.equals("")){
            this.email = email;
        }
        if(!password.equals("")){
            this.password = password;
        }
    }
}
