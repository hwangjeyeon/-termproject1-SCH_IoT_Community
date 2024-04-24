package com.schiot.community.controller;



import com.schiot.community.entity.Member;
import com.schiot.community.entity.MemberComment;
import com.schiot.community.entity.MemberPost;
import com.schiot.community.form.CommentForm;
import com.schiot.community.form.WriteForm;
import com.schiot.community.service.UsingMemberSessionService;
import com.schiot.community.service.WriteContentService;
import com.schiot.community.service.WriteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommunityController {

    private final WriteContentService writeContentService;
    private final UsingMemberSessionService usingMemberSessionService;
    private final WriteService writeService;

    //TODO: 사용자가 작성할 글 정보 post 컨트롤러 구현 및 저장 서비스 구현, 게시글 번호를 게시글 전체 수 + 1로 저장하는 기능 구현

    @GetMapping("/writelist")
    public String writeList(Model model, HttpServletRequest request) {
        model.addAttribute("posts",writeContentService.getWriteContent());
        if(usingMemberSessionService != null){
            model.addAttribute("member", usingMemberSessionService.
                    getSessionMember((Member)request.getSession().getAttribute("loginMember")));
        }
        log.info("게시글 목록");
        return "writelist";
    }

    @GetMapping("/content/{postNumber}")
    public String memberContent(@PathVariable Integer postNumber,
                                @RequestParam(name = "studentId") String studentId,
                                Model model){
        MemberPost memberPost = writeContentService.getMemberPostContent(postNumber, studentId);
        List<MemberComment> memberCommentList = writeContentService.getPostComments(memberPost);
        model.addAttribute("posts",memberPost);
        model.addAttribute("comments", memberCommentList);
        model.addAttribute("commentform", new CommentForm());
        writeContentService.increaseView(memberPost);


        return "writecontent";
    }



    // 인증 관문
    @GetMapping("/write/{studentId}")
    public String memberWrite(@PathVariable String studentId,
                              @RequestParam(name = "password") String password){

        return "redirect:/reloadWrite";
    }

    @GetMapping("/reloadWrite")
    public RedirectView reloadWrite(){
        return new RedirectView("write");
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("writeform", new WriteForm());
        return "write";
    }

    @PostMapping("/write")
    public String writes(@ModelAttribute("writeform") WriteForm writeForm,
                         HttpServletRequest request){
        if(!writeService.writeFormCheck(writeForm)){
            log.info("내용이 없으면 안 됩니다.");
            return "redirect:/write";
        }
        writeService.uploadWriteContent(writeForm, usingMemberSessionService.
                getSessionMember((Member)request.getSession().getAttribute("loginMember")));
        return "redirect:/writelist";
    }

    @PostMapping("/content/{postNumber}/comment")
    public String memberComment(
            @PathVariable Integer postNumber,
            @RequestParam(name = "studentId") String studentId,
            @ModelAttribute("commentform") CommentForm commentForm){
        log.info("{}", commentForm.getContent());
        if(!writeService.commentFormCheck(commentForm)){
            log.info("댓글 내용이 없으면 안 됩니다.");
            return "redirect:/content/" + postNumber +"?studentId=" + studentId;
        }
        MemberPost memberPost = writeContentService.getMemberPostContent(postNumber, studentId);
        writeService.uploadComment(commentForm, memberPost);
        log.info("댓글 등록 완료, 내용 = {}", commentForm.getContent());
        return "redirect:/content/" + postNumber +"?studentId=" + studentId;
    }


}
