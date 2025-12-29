<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录 - 讨论帖系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
    <h1>用户登录</h1>

    <!-- 显示注册成功提示 -->
    <% if (request.getAttribute("success") != null) { %>
    <div style="color: #28a745; text-align: center; margin-bottom: 15px;"><%= request.getAttribute("success") %></div>
    <% } %>

    <!-- 显示登录错误提示 -->
    <% if (request.getAttribute("error") != null) { %>
    <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>

    <!-- 登录表单 -->
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" id="username" name="username" required placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" id="password" name="password" required placeholder="请输入密码">
        </div>
        <div class="form-group">
            <label for="captcha">验证码</label>
            <div style="display: flex; gap: 10px; align-items: center;">
                <input type="text" id="captcha" name="captcha" required placeholder="请输入验证码">
                <img src="${pageContext.request.contextPath}/captcha" alt="验证码" onclick="this.src='${pageContext.request.contextPath}/captcha?'+Math.random()" style="cursor: pointer;">
            </div>
            <small>点击图片刷新验证码</small>
        </div>
        <button type="submit">登录</button>
    </form>

    <!-- 新增：注册入口链接 -->
    <div style="margin-top: 20px; text-align: center;">
        还没有账号？<a href="${pageContext.request.contextPath}/register">立即注册</a>
    </div>
</div>
</body>
</html>