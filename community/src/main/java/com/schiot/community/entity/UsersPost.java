package com.schiot.community.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "USERS_POST")
public class UsersPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERS_POST_ID")
    private Long id;

    @Column(name = "POST_NUMBER")
    private String postNumber;
    @Column(name = "POST_TITLE")
    private String postTitle;
    @Column(name = "POST_VIEW")
    private Integer postView;
    @Column(name = "POST_CONTENT")
    private String postContent;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User postUser;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_COMMENT_ID")
    private UsersComment postComment;


}
