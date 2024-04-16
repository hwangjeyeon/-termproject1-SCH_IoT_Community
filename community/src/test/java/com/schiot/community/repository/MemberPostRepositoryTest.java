package com.schiot.community.repository;

import com.schiot.community.entity.MemberPost;
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
class MemberPostRepositoryTest {

    @Autowired
    MemberPostRepository memberPostRepository;

    @DisplayName("유저 게시글 저장 잘 되는지 테스트")
    @Test
    @Transactional
    void test1(){
        MemberPost memberPost = MemberPost.builder()
                .id(1L)
                .postTitle("안녕하세요")
                .postContent("안녕하세요 사물인터넷학과 학생입니다.")
                .postNumber(1)
                .postView(5).build();
        memberPostRepository.save(memberPost);

        Optional<MemberPost> tmp = memberPostRepository.findById(memberPost.getId());
        assertThat(tmp).isPresent();
        MemberPost findPost = tmp.get();
        assertThat(memberPost.getId()).isEqualTo(findPost.getId());
        assertThat(memberPost.getPostTitle()).isEqualTo(findPost.getPostTitle());
        assertThat(memberPost.getPostContent()).isEqualTo(findPost.getPostContent());
        assertThat(memberPost.getPostNumber()).isEqualTo(findPost.getPostNumber());
        assertThat(memberPost.getPostView()).isEqualTo(findPost.getPostView());
    }



}