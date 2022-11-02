package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    //GetMapping - 단순 조회
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //PostMapping - 삽입
    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(ModelMap model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
