package com.example.introspring;

import com.example.introspring.aop.TimeTraceAop;
import com.example.introspring.repository.*;
import com.example.introspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 자바 코드로 직접 스프링 빈 등록하기 **/
@Configuration // Spring 컨테이너가 SpringConfig를 관리하도록 함
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    private DataSource dataSource; // 데이터베이스 커넥션을 획득할 때 사용하는 객체
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean // 스프링 빈을 등록하겠다는 뜻
    public MemberService memberService(){ // memberService 등록
        return new MemberService(memberRepository); // 스프링 빈에 등록되어있는 memberRepository를 넣어줌
    }

//    @Bean // 스프링 빈을 등록하겠다는 뜻
//    public MemberRepository memberRepository(){ // memberRepository 등록
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
