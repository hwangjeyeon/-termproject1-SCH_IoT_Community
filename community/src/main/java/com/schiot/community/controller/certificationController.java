package com.schiot.community.controller;

import com.schiot.community.form.LoginForm;
import com.schiot.community.form.RegisterForm;
import com.schiot.community.service.LoginService;
import com.schiot.community.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class certificationController {

    private final LoginService loginService;
    private final RegisterService registerService;

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginform",new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginform") LoginForm loginForm) {
        log.info("{} {}",loginForm.getMemberId(),loginForm.getMemberPassword());
        // 에러를 폼에 담아서 처리할 것
        if(!loginService.loginCheckService(loginForm)){
            return "redirect:/login";
        }
        log.info("로그 성공");
        return "redirect:/writelist";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerform", new RegisterForm());
        return "register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm){
        log.info("{} {}",registerForm.getMemberId(),registerForm.getMemberPassword());
        if(!registerService.RegisterCheck(registerForm)){
            return "redirect:/register";
        }
        log.info("{} 등록", registerForm.getStudentId());
        return "redirect:/login";
    }

    @GetMapping("/writelist")
    public String writeList(){

        return "writelist";
    }



}
