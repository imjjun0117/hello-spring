package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//테스트 할 클래스 ctrl+shift+t -> 테스트 클래스 자동생성
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //테스트마다 인스턴스 객체가 생기는 것을 방지
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //생성자를 통해 memberRepository가 생성 -> Dependency Injection(DI)
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    //테스트 메소드명은 한글로 작성가능 why? build시 테스트 코드는 포함되지 않음
    @Test
    void 회원가입() {
        //주석을 통해 given when then으로 나누어두면 가독성 향상!!
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //테스트 실패시 예외 처리도 중요함
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
       /* try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123123");
        }
        */

        //위에 테스트를 더 간단하게 수행하기 위해 제공되는 것이 있음
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then

    }

    @Test
    void findMembers() {


    }

    @Test
    void findOne() {
    }


}