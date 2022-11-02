package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    //index.html이 웰컴 페이지이지만 URL 맵핑을 우선순위로 둔다다
   @GetMapping("/")
    public String home() {
        return "home";
    }
}
