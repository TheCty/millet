<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <form action="${pageContext.request.contextPath}/shopping?method=insert"method="post">
        <img src="/img/${commodity.img}" alt="" width="400px">

        <p><input type="hidden" name="id" value="${commodity.id}"></p>

        <p>${commodity.name}</p>

        <p>${commodity.price}</p>

        <p>${commodity.context}</p>

        <p><input type="number" max="${commodity.count}" value="1" name="count"></p>

        <p>
            ${msg}
            <input type="submit" value="加入购物车">
            <a href="">立即购买</a>
        </p>
    </form>
</div>



</body>
</html>
