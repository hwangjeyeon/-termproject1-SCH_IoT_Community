package com.schiot.community.service;


import com.schiot.community.entity.Member;
import com.schiot.community.form.RegisterForm;
import com.schiot.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService {
    private final MemberRepository memberRepository;

    public boolean RegisterCheck(RegisterForm registerForm){
        List<Member> findByMemberId = memberRepository.findByMemberId(registerForm.getMemberId());
        List<Member> findByStudentId = memberRepository.findByStudentId(registerForm.getStudentId());
        if(!findByMemberId.isEmpty() || !findByStudentId.isEmpty()){
            log.info("중복된 회원가입 정보 입력");
            return false;
        }
        Member registerMember = Member.builder()
                .memberId(registerForm.getMemberId())
                .memberPassword(registerForm.getMemberPassword())
                .studentId(registerForm.getStudentId())
                .memberStatus(registerForm.getMemberStatus())
                .build();
        memberRepository.save(registerMember);
        log.info("회원가입 성공!");
        return true;
    }


}
