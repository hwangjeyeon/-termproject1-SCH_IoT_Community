package com.schiot.community.entity.enums;


/**
 FRESHMAN: 1학년
 SOPHOMORE: 2학년
 JUNIOR: 3학년
 SENIOR 4학년
 BACHELOR: 졸업생 (학사이상)
 PROFESSOR: 교수
 */
public enum MemberStatus {

    FRESHMAN("1학년"),
    SOPHOMORE("2학년"),
    JUNIOR("3학년"),
    SENIOR("4학년"),
    BACHELOR("졸업생"),
    PROFESSOR("교수님");

    private final String message;

    MemberStatus(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }



}