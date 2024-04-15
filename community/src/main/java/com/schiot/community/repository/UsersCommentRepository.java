package com.schiot.community.repository;

import com.schiot.community.entity.UsersComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersCommentRepository extends JpaRepository<UsersComment, Long> {
}
