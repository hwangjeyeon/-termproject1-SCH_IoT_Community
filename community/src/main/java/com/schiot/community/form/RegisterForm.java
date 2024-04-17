package com.schiot.community.form;

import com.schiot.community.entity.enums.MemberStatus;
import lombok.Getter;

@Getter
public class RegisterForm {

    private String memberId;
    private String memberPassword;
    private String studentId;
    private MemberStatus memberStatus;

}
