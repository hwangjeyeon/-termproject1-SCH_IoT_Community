package com.schiot.community.service;


import com.schiot.community.entity.MemberComment;
import com.schiot.community.entity.MemberPost;
import com.schiot.community.repository.MemberCommentRepository;
import com.schiot.community.repository.MemberPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WriteContentService {

    private final MemberPostRepository memberPostRepository;
    private final MemberCommentRepository memberCommentRepository;

    public List<MemberPost> getWriteContent(){
        return memberPostRepository.findAll();
    }

    public MemberPost getMemberPostContent(Integer postNumber, String studentId){
        List<MemberPost> memberPosts = memberPostRepository.findByPostNumberAndPostMember_StudentId(postNumber, studentId);
        return memberPosts.get(0);
    }

    //@Transactional
    public List<MemberComment> getPostComments(MemberPost memberPost){
        List<MemberComment> memberComments = memberCommentRepository.findByCommentsPost(memberPost);
        return memberComments;
    }

    public void increaseView(MemberPost memberPost){
        memberPost.increaseView();
        memberPostRepository.save(memberPost);
    }


}
