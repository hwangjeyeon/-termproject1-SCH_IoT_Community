package com.schiot.community.repository;

import com.schiot.community.entity.MemberPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPostRepository extends JpaRepository<MemberPost, Long> {
}
