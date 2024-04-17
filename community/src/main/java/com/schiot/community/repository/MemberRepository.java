package com.schiot.community.repository;

import com.schiot.community.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByMemberId(String memberId);
    List<Member> findByMemberPassword(String memberPassword);
    List<Member> findByStudentId(String studentId);
}
