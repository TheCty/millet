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
<script type="text/javascript">
    function paging() {
        document.getElementById("my").trigger("submit");
    }
</script>

<script src="/lib/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //获得所选中的值
        const shu = $("[name='classify']").attr("data");
        $("[name='classify']").attr("data",shu).val(shu);

    });
</script>

<body>

<div class="img">

    <div><a href="${pageContext.request.contextPath}/homepage?method=exit">退出用户</a></div>
    <div><a href="${pageContext.request.contextPath}/shopping?method=show">购物车</a></div>
    <div><a href="/forbid/addCommodity.jsp">添加商品</a></div>

    <form action="${pageContext.request.contextPath}/homepage?method=select" method="post" id="my" >
        名称：<input type="text" name="name" value="${comm.name==null?null:comm.name}">
        类别：
        <select name="classify" data="${comm.classifyId==null?0:comm.classifyId.id}">
            <option value="0">==请选择==</option>
            <c:forEach items="${classifyList}" var="i">
                <option value="${i.id}" >${i.name}</option>
            </c:forEach>
        </select>

        <input type="submit"  value="提交">

        <br>
        <input type="hidden" value="${p.pageNow==null?1:p.pageNow}" name="pageNow">
        <c:forEach items="${commList}" var="i">
            <div class="item">
                <a href="${pageContext.request.contextPath}/commodity?id=${i.id}">
                    <img src="../img/${i.img}" alt="">
                </a>
                <p>${i.name}</p>
                <p style="color: red">${i.price}</p>
                <p>${i.context}</p>
            </div>
        </c:forEach>
    </form>

    <div>
        <a href="${pageContext.request.contextPath}/homepage?method=select&name=${comm.name}&classify=${comm.classifyId.id==null?0:comm.classifyId.id}&pageNow=1" onclick="paging()">首页</a>
        <a href="${pageContext.request.contextPath}/homepage?method=select&name=${comm.name}&classify=${comm.classifyId.id==null?0:comm.classifyId.id}&pageNow=${p.pageNow-1}" onclick="paging()">上一页</a>
        <p style="display: inline-block">当前页数 ${p.pageNow} 页/${p.totalPage} 页</p>
        <a href="${pageContext.request.contextPath}/homepage?method=select&name=${comm.name}&classify=${comm.classifyId.id==null?0:comm.classifyId.id}&pageNow=${p.pageNow+1}" onclick="paging()">下一页</a>
        <a href="${pageContext.request.contextPath}/homepage?method=select&name=${comm.name}&classify=${comm.classifyId.id==null?0:comm.classifyId.id}&pageNow=${p.totalPage}" onclick="paging()">尾页</a>
    </div>
</div>




</body>
</html>
