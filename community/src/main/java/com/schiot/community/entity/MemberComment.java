package com.schiot.community.entity;


import jakarta.persistence.*;
import lombok.*;


/**
 * 추후 AUDITION을 통해 생성일도 추가 필요
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "MEMBER_COMMENT")
public class MemberComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_COMMENT_ID")
    private Long id;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member commentsMember;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_POST_ID")
    private MemberPost commentsPost;

}
