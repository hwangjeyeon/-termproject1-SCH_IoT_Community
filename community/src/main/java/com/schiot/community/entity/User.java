package com.schiot.community.entity;


import com.schiot.community.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_PRIVATE_ID")
    private String userId;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "STUDENT_ID")
    private String StudentId;

    @Column(name = "USER_STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


    @OneToMany(mappedBy = "postUser")
    private List<UsersPost> usersPosts = new ArrayList<>();

    @OneToMany(mappedBy = "commentsUser")
    private List<UsersComment> usersComments = new ArrayList<>();

}
