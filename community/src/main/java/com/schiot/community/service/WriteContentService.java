package com.schiot.community.service;


import com.schiot.community.entity.MemberPost;
import com.schiot.community.repository.MemberPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WriteContentService {

    private final MemberPostRepository memberPostRepository;

    public List<MemberPost> getWriteContent(){
        return memberPostRepository.findAll();
    }

    public MemberPost getMemberPostContent(Integer postNumber, String studentId){
        List<MemberPost> memberPosts = memberPostRepository.findByPostNumberAndPostMember_StudentId(postNumber, studentId);
        return memberPosts.get(0);
    }

    public void increaseView(MemberPost memberPost){
        memberPost.increaseView();
        memberPostRepository.save(memberPost);
    }


}
