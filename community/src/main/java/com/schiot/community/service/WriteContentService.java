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



}
