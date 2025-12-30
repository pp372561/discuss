package com.controller;

import com.model.User;
import com.service.QaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login") 
public class LoginController {
    @Autowired
    private QaService qaService;

    // GET请求：跳转到登录页
    @GetMapping
    public String loginPage() {
        return "login"; 
    }

    // POST请求：处理登录
    @PostMapping
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String captcha,
            HttpSession session,
            HttpServletRequest req
    ) {
 
        if (username == null || password == null || captcha == null) {
            req.setAttribute("error", "请填写完整信息");
            return "login";
        }

        String realCaptcha = qaService.getCaptcha(session.getId());
        if (realCaptcha == null || !realCaptcha.equalsIgnoreCase(captcha)) {
            qaService.removeCaptcha(session.getId());
            req.setAttribute("error", "验证码错误");
            return "login";
        }

        User user = qaService.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            req.setAttribute("error", "用户名或密码错误");
            return "login";
        }

        session.setAttribute("loginUser", username);
        qaService.removeCaptcha(session.getId());
        return "redirect:/thread/list"; // 重定向到帖子列表
    }

}
