package com.schiot.community.repository;

import com.schiot.community.entity.MemberComment;
import com.schiot.community.entity.MemberPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberCommentRepository extends JpaRepository<MemberComment, Long> {

    List<MemberComment> findByCommentsPost(MemberPost memberPost);

}
