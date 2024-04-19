package com.schiot.community.service;


import com.schiot.community.entity.Member;
import com.schiot.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsingMemberSessionService {

    private final MemberRepository memberRepository;

    public Member getSessionMember(Member sessionMember){
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            if(member.getMemberPassword().equals(sessionMember.getMemberPassword())){
                return member;
            }
        }

        return null;
    }


}
