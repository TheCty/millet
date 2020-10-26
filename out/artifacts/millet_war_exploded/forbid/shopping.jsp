<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<style type="text/css">
    *{
        padding: 0;
        margin: 0;
    }
    .img{
        padding: 20px;
        margin: auto;
        width: 1000px;
        overflow: hidden;
        border: 1px blue solid;
        box-sizing: border-box;
    }
    .item{
        width: 230px;
        height: 400px;
        float: left;
        margin: 3px;
        border: 1px solid red;
        box-sizing: border-box;
        padding: 10px;

    }
    .item a{
        display: block;
        text-align: center;

        box-sizing: content-box;
    }
    .item a img{
        width: 200px;
    }
</style>

<script src="../lib/jquery.js" type="text/javascript" charset="utf-8"></script>
<!--复选框全选全不选-->
<script type="text/javascript">
    //让后面的复选框和第一个一致即可
    function c(obj) {
        $(".cb").prop("checked", obj.checked)
    }
    //当下面所有的复选框全选之后自动选择最上面的那个复选框
    $(function() {
        $("table td :checkbox").click(function() {
            var c = $("table td :checkbox").length;
            var len = $("table td :checked").length;
            $("table th :checkbox").prop("checked", c == len);
        })
    });

    //表格颜色代码
    $("tr:even").css("background", "pink")
    $("tr:odd").css("background", "yellow")

</script>


<body>

<div class="img">

    <table border="1">
        <tr>
            <th>
                <input type="checkbox" onclick="c(this)">
            </th>
            <th>商品</th>
            <th>数量</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${shoppingList}" var="i" >
            <tr>
                <td>
                    <input type="checkbox" class="cb">
                </td>
                <td>${i.commodityIds}</td>
                <td><input type="text" value="${i.counts}"></td>
                <td><a href="${pageContext.request.contextPath}/shopping?method=delete&id=${i.id}">删除</a></td>
            </tr>

        </c:forEach>



    </table>


</div>

</body>
</html>
