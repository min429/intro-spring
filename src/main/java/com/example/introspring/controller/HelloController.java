package com.example.introspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** 요청을 받는 곳 **/
@Controller // Spring 컨테이너가 HelloController를 관리하도록 함
public class HelloController {

    // 템플릿 엔진 방식 -> 변환된 html코드가 전달됨
    
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    /*
    * 1. 브라우저에서 hello-mvc 요청 -> 내장 톰켓서버에서 controller 내에 "hello-mvc"가 있는지 찾음
    * 2. controller 내에 없으면 resources에서 hello-mvc.html 찾아서 웹 브라우저에 그대로 넘김
    * 3. 여기서는 controller 내에 있으므로 HelloController에서 name 속성값 부여 후 "hello-template"(html이름) 반환
    * 4. viewResolver가 hello-template.html의 name값을 변경 후 웹 브라우저에 넘김
    */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        // 도메인에 http://localhost:8080/hello-mvc?name=spring!!!라고 치면 spring!!!이 name 파라미터로 전달됨
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식 -> 그냥 데이터가 그대로 전달됨 // @ResponseBody 필요

    @GetMapping("hello-string")
    @ResponseBody // html <body>에 데이터 직접 넣는 거랑 똑같음
    public String helloString(@RequestParam("name") String name){
        // 도메인에 http://localhost:8080/hello-string?name=spring!!!라고 치면 spring!!!이 name 파라미터로 전달됨
        return "hello " + name; // hello spring!!! // 문자 그대로 전달
    }

    @GetMapping("hello-api")
    @ResponseBody
    // 도메인에 http://localhost:8080/hello-api?name=spring!!!라고 치면 spring!!!이 name 파라미터로 전달됨
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // hello객체를 Jason형태로 전달
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
