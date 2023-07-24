package com.bestswlkh0310.google0auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/authentication-fail")
    public String authenticationFail() {
        return "errorPage/authenticationFail";
    }

    @GetMapping("/authorization-fail")
    public String authorizationFail() {
        return "errorPage/authorizationFail";
    }
}
