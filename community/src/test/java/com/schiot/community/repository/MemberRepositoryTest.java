package com.schiot.community.repository;

import com.schiot.community.entity.Member;
import com.schiot.community.entity.enums.MemberStatus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @DisplayName("멤버 정보들이 잘 저장되는지 테스트")
    @Test
    @Transactional
    void test1(){
        Member member = Member.builder().id(1L)
                .memberId("hwang")
                .memberPassword("jeyeon")
                .StudentId("20191511")
                .memberStatus(MemberStatus.SENIOR)
                .build();
        memberRepository.save(member);

        Optional<Member> tmp = memberRepository.findById(member.getId());
        assertThat(tmp).isPresent();
        Member findMember = tmp.get();

        assertThat(member.getId()).isEqualTo(findMember.getId());
        assertThat(member.getMemberId()).isEqualTo(findMember.getMemberId());
        assertThat(member.getMemberPassword()).isEqualTo(findMember.getMemberPassword());
        assertThat(member.getStudentId()).isEqualTo(findMember.getStudentId());
        assertThat(member.getMemberStatus()).isEqualTo(findMember.getMemberStatus());
    }

    
}