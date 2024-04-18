package com.schiot.community.service;

import com.schiot.community.entity.Member;
import com.schiot.community.form.LoginForm;
import com.schiot.community.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
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

        List<Member> findMember = memberRepository.findByMemberPassword(loginForm.getMemberPassword());
        if(findMember.isEmpty()){
            log.info("로그인 실패");
            return false;
        }
        log.info("로그인 성공");
        return true;
    }

    public Member loginSessionService(LoginForm loginForm){
        List<Member> findMember = memberRepository.findByMemberPassword(loginForm.getMemberPassword());
        return findMember.get(0);
    }


}
