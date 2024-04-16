package com.schiot.community.repository;

import com.schiot.community.entity.MemberComment;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberCommentRepositoryTest {

    @Autowired
    MemberCommentRepository memberCommentRepository;

    @DisplayName("멤버 댓글이 잘 저장되는지 테스트")
    @Test
    @Transactional
    void test1(){
        MemberComment memberComment = MemberComment.builder()
                .id(1L)
                .commentContent("반가워요!")
                .build();

        memberCommentRepository.save(memberComment);
        Optional<MemberComment> findComment = memberCommentRepository.findById(1L);
        assertThat(findComment).isPresent();

        assertThat(memberComment.getId()).isEqualTo(findComment.get().getId());
        assertThat(memberComment.getCommentContent()).isEqualTo(findComment.get().getCommentContent());
    }

}