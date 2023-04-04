package com.example.introspring.controller;

public class MemberForm {
    private String name; // home.html안에 웹페이지에서 입력한 값의 key값인 "name"에 대응됨 -> 입력값이 MemberForm 객체의 name에 저장됨

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}