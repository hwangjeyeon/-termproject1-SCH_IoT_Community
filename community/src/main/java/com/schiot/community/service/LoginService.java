package com.schiot.community.service;

import com.schiot.community.entity.Member;
import com.schiot.community.form.LoginForm;
import com.schiot.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public boolean loginCheckService(LoginForm loginForm){
        log.info("{}",loginForm.getMemberPassword());

        List<Member> findMember = memberRepository.findByMemberPassword(loginForm.getMemberPassword());
        if(findMember.isEmpty()){
            return false;
        }
        return true;
    }


}
