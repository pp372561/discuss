<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.DiscussionThread" %>
<%@ page import="com.model.Reply" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>帖子详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
    <div class="nav">
        <a href="${pageContext.request.contextPath}/thread/list">返回列表</a>
        <% if (session.getAttribute("loginUser") != null) { %>
        <a href="${pageContext.request.contextPath}/thread/publish">发布新帖</a>
        <% } %>
    </div>
    <% DiscussionThread thread = (DiscussionThread) request.getAttribute("thread"); %>
    <% if (thread == null) { %>
    <h1>帖子不存在</h1>
    <% } else { %>
    <h1><%= thread.getTitle() %></h1>
    <div class="thread-meta">
        作者：<%= thread.getAuthor() %> | 创建时间：<%= thread.getCreateTime() %>
    </div>
    <div style="margin: 20px 0; padding: 15px; background-color: #f9f9f9; border-radius: 4px;">
        <%-- 保留换行：将换行符替换为<br> --%>
        <%= thread.getContent().replace("\n", "<br>") %>
    </div>

    <%-- 发布回复 --%>
    <% if (session.getAttribute("loginUser") != null) { %>
    <h2>发布回复</h2>
    <form method="post" action="${pageContext.request.contextPath}/reply/publish">
        <input type="hidden" name="threadId" value="<%= thread.getId() %>">
        <div class="form-group">
            <label for="replyContent">回复内容</label>
            <textarea id="replyContent" name="content" required></textarea>
        </div>
        <button type="submit">提交回复</button>
    </form>
    <% } %>

    <%-- 回复列表 --%>
    <div class="reply-list">
        <h2>回复列表</h2>
        <% List<Reply> replies = thread.getReplies(); %>
        <% if (replies == null || replies.isEmpty()) { %>
        <p>暂无回复，快来抢沙发！</p>
        <% } else { %>
        <% for (Reply reply : replies) { %>
        <div class="reply-item">
            <div><%= reply.getContent().replace("\n", "<br>") %></div>
            <div class="reply-meta">
                回复者：<%= reply.getAuthor() %> | 回复时间：<%= reply.getCreateTime() %>
            </div>
        </div>
        <% } %>
        <% } %>
    </div>
    <% } %>
</div>
</body>
</html>