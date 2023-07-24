package com.bestswlkh0310.google0auth.controllerAdvice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class LoginControllerAdvice {

    @ModelAttribute
    public void addModel(Model model, HttpServletRequest request) {
        String requestUri = request.getRequestURI();

        if (requestUri.contains("api")) {
            return;
        }

        model.addAttribute("loginType", "/localhost:8080");
        model.addAttribute("pageName", "구글 로그인");
    }
}