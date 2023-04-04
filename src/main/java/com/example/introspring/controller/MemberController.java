package com.example.introspring.controller;

import com.example.introspring.domain.Member;
import com.example.introspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/** 요청을 받는 곳 **/
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // 자동으로 의존관계 주입(MemberService를 쓸 수 있도록)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 기본적으로 도메인 창에 입력한 -> GetMapping
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 웹 페이지에서 클릭으로 이동 -> PostMapping
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName()); // 웹페이지에서 입력한 이름으로 세팅

        memberService.join(member); // 회원가입

        return "redirect:/"; // 홈 화면으로 돌려보냄
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}