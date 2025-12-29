<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布讨论帖</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
    <div class="nav">
        <a href="${pageContext.request.contextPath}/thread/list">返回列表</a>
    </div>
    <h1>发布讨论帖</h1>
    <form method="post" action="${pageContext.request.contextPath}/thread/publish">
        <div class="form-group">
            <label for="title">标题</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="content">内容</label>
            <textarea id="content" name="content" required></textarea>
        </div>
        <button type="submit">发布</button>
    </form>
</div>
</body>
</html>