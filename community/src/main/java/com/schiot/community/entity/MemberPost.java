package com.schiot.community.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


/**
 * 추후 AUDITION을 통해 생성일도 추가 필요
 */
@Entity
@Getter @Setter
@Table(name = "MEMBER_POST")
public class MemberPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_POST_ID")
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
    @JoinColumn(name = "MEMBER_ID")
    private Member postMember;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_COMMENT_ID")
    private MemberComment postComment;


}
