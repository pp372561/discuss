<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册 - 讨论帖系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
    <h1>用户注册</h1>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/register">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" id="username" name="username" required placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" id="password" name="password" required placeholder="请输入密码">
        </div>
        <div class="form-group">
            <label for="confirmPwd">确认密码</label>
            <input type="password" id="confirmPwd" name="confirmPwd" required placeholder="请再次输入密码">
        </div>
        <button type="submit">提交注册</button>
    </form>

    <-- 跳转到登录页的链接 -->
    <div style="margin-top: 20px; text-align: center;">
        已有账号？<a href="${pageContext.request.contextPath}/login">立即登录</a>
    </div>
</div>
</body>

</html>
