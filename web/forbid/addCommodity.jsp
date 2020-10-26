<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <p>图片:  <input type="file" name="img"></p>
        <p>名称:  <input type="text" name="name"></p>
        <p>类别:
            <select name="classify" >
                <option value="0">==请选择==</option>
                <c:forEach items="${classifyList}" var="i">
                    <option value="${i.id}" >${i.name}</option>
                </c:forEach>
            </select>
        </p>

        <p>数量:  <input type="text" name="count"></p>
        <p>金额:  <input type="text" name="price"></p>
        <p>详细:  <input type="text" name="context"></p>

        <p> <input type="submit" value="提交"></p>
    </form>
</div>

</body>
</html>
