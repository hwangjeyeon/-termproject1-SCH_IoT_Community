package com.schiot.community.form;

import com.schiot.community.entity.enums.MemberStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterForm {

    private String memberId;
    private String memberPassword;
    private String studentId;
    private MemberStatus memberStatus;

}
