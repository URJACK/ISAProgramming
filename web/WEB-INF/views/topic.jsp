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
</head>
<body>
<jsp:include page="/topbar.jsp"></jsp:include>
<div class="container" id="background" style="display: none">
    <div class="row">
        <label class="text-primary" style="font-size: 26px" >ISA 论坛</label>
    </div>
    <div class="row">
        <div class="col-md-8">
            <table class="table table-bordered table-hover">
                <caption>最新</caption>
                <thead>
                <tr>
                    <th style="display: none;"></th>
                    <th>Title</th>
                    <th>Owner</th>
                </tr>
                </thead>
                <tbody id="topic_tbody">
                <tr>
                    <td style="display: none;">2</td>
                    <td>Android</td>
                    <td>Sifu Tang</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-md-4">
            <table class="table table-bordered">
                <caption>你的信息</caption>
                <thead>
                <tr>
                    <th id="topic_account">Debug</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>已经发贴:<a href="#" class="text-primary" id="topic_create">26</a></td>
                </tr>
                <tr>
                    <td>跟帖数:<a href="#" class="text-primary" id="topic_follow">0</a></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="/js/topic.js"></script>
</body>
</html>
