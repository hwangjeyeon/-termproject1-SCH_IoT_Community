package com.schiot.community.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "USERS_COMMENT")
public class UsersComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERS_COMMENT_ID")
    private Long id;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User commentsUser;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_POST_ID")
    private UsersPost commentsPost;

}
