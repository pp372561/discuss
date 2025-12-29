package com.controller;

import com.service.QaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register") // 路径与原有一致：/register
public class RegisterController {
    @Autowired
    private QaService qaService;

    // GET请求：跳转到注册页
    @GetMapping
    public String registerPage() {
        return "register"; // 对应/WEB-INF/views/register.jsp
    }

    // POST请求：处理注册
    @PostMapping
    public String doRegister(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPwd,
            HttpServletRequest req
    ) {
        // 完全复用原有逻辑
        if (username == null || password == null || confirmPwd == null
                || username.trim().isEmpty() || password.trim().isEmpty() || confirmPwd.trim().isEmpty()) {
            req.setAttribute("error", "请填写完整的注册信息！");
            return "register";
        }
        if (!password.equals(confirmPwd)) {
            req.setAttribute("error", "两次输入的密码不一致！");
            return "register";
        }

        boolean isSuccess = qaService.registerUser(username, password);
        if (!isSuccess) {
            req.setAttribute("error", "用户名已存在，请更换用户名！");
            return "register";
        }

        req.setAttribute("success", "注册成功！请使用新账号登录");
        return "login"; // 跳转到登录页
    }
}