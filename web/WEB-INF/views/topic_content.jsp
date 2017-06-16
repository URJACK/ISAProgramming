<%--
  Created by IntelliJ IDEA.
  User: FuFangzhou
  Date: 2017/6/12
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>论坛</title>
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
    <script>
        var json = ${model};
    </script>
</head>
<body>
<jsp:include page="/topbar.jsp"></jsp:include>
<div class="container" id="background" style="display: none">
    <div class="row">
        <label id="topic_content_title" class="text-primary" style="font-size: 30px">标题</label>
    </div>
    <div class="row">
        <label id="topic_content_owner" class="text-danger" style="font-size: 20px">作者</label>
    </div>
    <div class="row">
        <div class="col-md-12 col-xs-12">
            <p class="text-info" readonly id="topic_content_content" style="font-size: 15px">
                这是一个普普通的帖子!
            </p>
        </div>
    </div>
    <div class="row">
        <form>
            <div class="form-group">
                <label>回复</label>
                <input type="text" placeholder="该帖子暂不允许回复" class="form-control" readonly>
            </div>
        </form>
    </div>
</div>
<script src="/js/topic_content.js"></script>
</body>
</html>
