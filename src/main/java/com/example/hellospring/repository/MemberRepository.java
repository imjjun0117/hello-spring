package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    // 데이터 저장소가 선정되지 않아 우선, 리포지토리는 인터페이스로 구현 클래스를 변경할 수 있도록 설계
    // 데이터 저장소는 RDB, NoSQL 등등 다양한 저장소를 고민중인 상황으로 가정
    // 개발을 진행하기 위해서 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소 사용

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
