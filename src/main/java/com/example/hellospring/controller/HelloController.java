package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(ModelMap model){
        model.addAttribute("data","hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        //ReponseBody
        //HTTP의 BODY에 문자 내용을 직접 반환
        //viewResolver 대신에 HttpMessageConverter가 동작
        //기본 문자 처리 : StringHttpMessageConverter
        //기본 객체 처리 : MappingJackson2HttpMessageConverter (JSON) 반환
        //byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
        //반환 데이터는 HTTP Accept 헤더에서 설정할 수 있음
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName(){

            return this.name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
