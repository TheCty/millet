<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style type="text/css">
    *{
        margin: 0;
        padding: 0;
    }
</style>
<body>
<h1>注册</h1>
<div>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <p>用户:<input type="text" name="username"></p>
        <p>密码:<input type="password" name="password"></p>
        <p>新密码:<input type="password" name="newPassword"></p>
        <p>${msg}</p>
        <p><input type="submit" value="提交"></p>
    </form>
</div>
</body>
</html>
