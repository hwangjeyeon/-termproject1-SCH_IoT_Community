package com.schiot.community.interceptor;


import com.schiot.community.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class safeWriteInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Member member = (Member) request.getSession().getAttribute("loginMember");
        if(member == null || !member.getMemberPassword().equals(request.getParameter("password"))){
            log.info("다른 사용자 인증 정보로 게시글 작성 요청 차단");
            response.sendRedirect("/writelist");
            return false;
        }
        return true;
    }
}
