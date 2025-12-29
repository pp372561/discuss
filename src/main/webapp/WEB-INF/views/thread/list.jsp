<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.DiscussionThread" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>讨论帖列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
    <div class="nav">
        <% String loginUser = (String) session.getAttribute("loginUser"); %>
        <% if (loginUser != null) { %>
        <span>欢迎, <%= loginUser %></span>
        <a href="${pageContext.request.contextPath}/thread/publish">发布帖子</a>
        <a href="${pageContext.request.contextPath}/logout">退出登录</a>
        <% } %>
    </div>
    <h1>讨论帖列表</h1>
    <% List<DiscussionThread> threads = (List<DiscussionThread>) request.getAttribute("threads"); %>
    <% if (threads == null || threads.isEmpty()) { %>
    <p>暂无讨论帖，快来发布第一个吧！</p>
    <% } else { %>
    <ul class="thread-list">
        <% for (DiscussionThread thread : threads) { %>
        <li class="thread-item">
            <div class="thread-title">
                <a href="${pageContext.request.contextPath}/thread/detail?id=<%= thread.getId() %>">
                    <%= thread.getTitle() %>
                </a>
            </div>
            <div class="thread-meta">
                作者：<%= thread.getAuthor() %> | 创建时间：<%= thread.getCreateTime() %>
                | 回复数：<%= thread.getReplies() == null ? 0 : thread.getReplies().size() %>
            </div>
        </li>
        <% } %>
    </ul>
    <% } %>
</div>
</body>
</html>