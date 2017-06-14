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
    <title>在线编程</title>
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
    <style>
        .panel-body img{
            height: 100px;
        }
    </style>
</head>
<body>
<jsp:include page="/topbar.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <label class="text-primary" style="font-size: 25px">ISA Online Program</label>
    </div>
    <div class="row">
        <div class="col-md-2">
            <ul class="list-group">
                <li class="list-group-item" id="select_questionset"><a href="#" class="text-primary">题目集</a></li>
                <li class="list-group-item" id="select_question"><a href="#" class="text-primary">题目列表</a></li>
                <li class="list-group-item" id="select_submit"><a href="#" class="text-primary">提交列表</a></li>
                <li class="list-group-item" id="select_rank"><a href="#" class="text-primary">Rank</a></li>
            </ul>
        </div>
        <div class="col-md-10" id="select_content">

        </div>
    </div>
</div>
<script src="/js/program.js"></script>
</body>
</html>
