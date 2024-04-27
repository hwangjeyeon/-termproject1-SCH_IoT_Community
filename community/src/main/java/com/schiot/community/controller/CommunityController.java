package com.schiot.community.controller;



import com.schiot.community.entity.Member;
import com.schiot.community.entity.MemberComment;
import com.schiot.community.entity.MemberPost;
import com.schiot.community.form.CommentForm;
import com.schiot.community.form.UpdateForm;
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
                                Model model,HttpServletRequest request){
        MemberPost memberPost = writeContentService.getMemberPostContent(postNumber, studentId);

        model.addAttribute("posts",memberPost);
        model.addAttribute("comments", writeContentService.getPostComments(memberPost));
        model.addAttribute("commentform", new CommentForm());
        model.addAttribute("member",  usingMemberSessionService.getSessionMember
                ((Member)request.getSession().getAttribute("loginMember")));
        writeContentService.increaseView(memberPost);


        return "writecontent";
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
            return "redirect:/write?writefail";
        }
        writeService.uploadWriteContent(writeForm, usingMemberSessionService.
                getSessionMember((Member)request.getSession().getAttribute("loginMember")));
        return "redirect:/writelist";
    }

    @PostMapping("/content/{postNumber}/comment")
    public String memberComment(
            @PathVariable Integer postNumber,
            @RequestParam(name = "studentId") String studentId,
            @ModelAttribute("commentform") CommentForm commentForm,
            HttpServletRequest request){

        if(!writeService.commentFormCheck(commentForm)){
            log.info("댓글 내용이 없으면 안 됩니다.");
            return "redirect:/content/" + postNumber +"?studentId=" + studentId +"&commentfail";
        }
        writeService.uploadComment(commentForm,
                writeContentService.getMemberPostContent(postNumber, studentId),
                usingMemberSessionService.getSessionMember
                        ((Member)request.getSession().getAttribute("loginMember")));

        return "redirect:/content/" + postNumber +"?studentId=" + studentId;
    }

    @PostMapping("/content/{postNumber}/delete")
    public String postDelete(
            @PathVariable Integer postNumber,
            @RequestParam(name = "studentId") String studentId
    ){
        writeService.deleteContent(postNumber,studentId);


        return "redirect:/writelist";
    }

    @PostMapping("/content/{postNumber}/comment/delete")
    public String commentDelete(
            @PathVariable Integer postNumber,
            @RequestParam(name = "studentId") String studentId,
            @RequestParam(name = "commentId") Long id
    ){
        writeService.deleteComment(postNumber, id);


        return "redirect:/content/" + postNumber +"?studentId=" + studentId;
    }

    @GetMapping("/content/{postNumber}/update")
    public String postUpdate(
            @PathVariable Integer postNumber,
            @RequestParam(name = "studentId") String studentId,
            Model model
            ){
        MemberPost memberPost = writeContentService.getMemberPostContent(postNumber, studentId);
        model.addAttribute("posts",memberPost);
        model.addAttribute("updateform", new UpdateForm());
        return "update";
    }

    @PostMapping("/content/{postNumber}/update")
    public String postUpdates(
            @PathVariable Integer postNumber,
            @RequestParam(name = "studentId") String studentId,
            @ModelAttribute("updateform") UpdateForm updateForm
    ){
        if(!writeService.updateFormCheck(updateForm)){
            log.info("빈 내용으로는 수정할 수 없습니다..");
            return "redirect:/content/" + postNumber +"?studentId=" + studentId + "&updatefail";
        }

        writeService.updateWriteContent(updateForm, postNumber, studentId);

        return "redirect:/content/" + postNumber + "?studentId=" + studentId;
    }


}
