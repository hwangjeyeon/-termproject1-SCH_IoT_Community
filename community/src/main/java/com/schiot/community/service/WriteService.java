package com.schiot.community.service;

import com.schiot.community.entity.Member;
import com.schiot.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WriteService {

    private final MemberRepository memberRepository;

    public Member getWriteMember(String studentId, String password){
        List<Member> member = memberRepository.findByStudentIdAndMemberPassword(studentId, password);
        return member.get(0);
    }


}
