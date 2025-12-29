package com.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout") // 路径与原有一致：/logout
public class LogoutController {
    @GetMapping
    public String logout(HttpSession session) {
        // 完全复用原有逻辑
        session.removeAttribute("loginUser");
        return "redirect:/login"; // 重定向到登录页
    }
}