package com.example.introspring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 알아서 생성해주는 경우 -> IDENTITY
    private Long id; // 시스템이 저장하는 id

//    @Column(name = "username"); // DB의 column명이 ussername과 매핑
    private String name; // 사용자 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
