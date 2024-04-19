package com.schiot.community.controller;



import com.schiot.community.entity.Member;
import com.schiot.community.entity.MemberPost;
import com.schiot.community.service.UsingMemberSessionService;
import com.schiot.community.service.WriteContentService;
import com.schiot.community.service.WriteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
                                @RequestParam(name = "studentId", required = false) String studentId,
                                Model model){
        MemberPost memberPost = writeContentService.getMemberPostContent(postNumber, studentId);
        model.addAttribute("posts",memberPost);
        writeContentService.increaseView(memberPost);


        return "writecontent";
    }

    @GetMapping("/write/{studentId}")
    public String memberWrite(@PathVariable String studentId,
                              @RequestParam(name = "password") String password,
                              RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("member",
                writeService.getWriteMember(studentId, password));
        return "redirect:/reloadWrite";
    }

    @GetMapping("/reloadWrite")
    public RedirectView reloadWrite(){
        return new RedirectView("write");
    }

    @GetMapping("/write")
    public String writes(){
        return "write";
    }


}
