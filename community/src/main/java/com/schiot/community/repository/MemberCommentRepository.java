package com.schiot.community.repository;

import com.schiot.community.entity.MemberComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCommentRepository extends JpaRepository<MemberComment, Long> {
}
