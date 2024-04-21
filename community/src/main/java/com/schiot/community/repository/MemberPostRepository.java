package com.schiot.community.repository;

import com.schiot.community.entity.MemberPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberPostRepository extends JpaRepository<MemberPost, Long> {

    List<MemberPost> findByPostNumberAndPostMember_StudentId(Integer postNumber, String studentId);
}
