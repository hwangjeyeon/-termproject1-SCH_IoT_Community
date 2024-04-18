package com.schiot.community.controller;



import com.schiot.community.service.WriteContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommunityController {

    private final WriteContentService writeContentService;

    @GetMapping("/writelist")
    public String writeList(Model model) {
        model.addAttribute("posts",writeContentService.getWriteContent());
        log.info("게시글 목록");
        return "writelist";
    }



}
