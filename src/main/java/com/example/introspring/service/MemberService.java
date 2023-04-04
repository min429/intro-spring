package com.example.introspring.service;

import com.example.introspring.domain.Member;
import com.example.introspring.repository.MemberRepository;
import com.example.introspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/** 로직(서비스) 구현 **/
//@Service // Spring 컨테이너가 MemberService를 관리하도록 함
@Transactional // JPA는 join들어올 때 모든 데이터 변경이 다 transaction안에서 실행되어야함
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired // 자동으로 의존관계 주입(MemberService를 쓸 수 있도록)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){ // 회원가입
        // 회원 이름 중복x
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 중복회원 검증
        memberRepository.findByName(member.getName())
                .ifPresent(m->{ // ifPresent의 인자(람다식) 정의
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // result가 null일 경우 IllegalStateException를 던지도록
                });
    }

    public List<Member> findMembers(){ // 전체 회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){ // 회원 조회
        return memberRepository.findById(memberId);
    }
}