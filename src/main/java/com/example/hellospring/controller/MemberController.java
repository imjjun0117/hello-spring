package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

//    @Autowired private MemberService memberService; //필드 주입 방식

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { // 생성자 주입 방식
        this.memberService = memberService;
    }

//    @Autowired
//    public void setMemberService(MemberService memberService) { // setter 주입 방식
//        //public으로 문제가 발생할 수 있음(임의로 수정)
//        this.memberService = memberService;
//    }

}
