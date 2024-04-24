package com.schiot.community.entity;


import com.schiot.community.entity.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "MEMBER")
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_PRIVATE_ID")
    private String memberId;
    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;
    @Column(name = "STUDENT_ID")
    private String studentId;

    @Column(name = "MEMBER_STATUS")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;


    @OneToMany(mappedBy = "postMember")
    private List<MemberPost> memberPosts = new ArrayList<>();

    @OneToMany(mappedBy = "commentsMember")
    private List<MemberComment> memberComments = new ArrayList<>();

}
