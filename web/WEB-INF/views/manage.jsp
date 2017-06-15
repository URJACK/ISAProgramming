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
    <title>题目</title>
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/topbar.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <ul id="main_tab" class="nav nav-tabs">
            <li class="active"><a href="#manage_user" data-toggle="tab">人员管理</a></li>
            <li><a href="#manage_question" data-toggle="tab">题库管理</a></li>
            <li><a href="#manage_topic" data-toggle="tab">帖子管理</a></li>
            <li><a href="#manage_match" data-toggle="tab">比赛管理</a></li>
        </ul>
    </div>
</div>
<script src="/js/manage.js"></script>
</body>
</html>
