package com.example.introspring.service;

import com.example.introspring.domain.Member;
import com.example.introspring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 메서드를 실행하기 전에 동작하는 콜백메서드
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트 메서드를 실행 할 때마다 동작하는 콜백메서드
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); // 테스트 메서드 실행 할 때마다 레파지토리 비워줌
    }

    @Test
    void 회원가입() {
        // given 뭔가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        // when 실행할 때
        Long saveId = memberService.join(member);

        // then 뭔가 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }

}