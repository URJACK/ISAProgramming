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
    <style>
        body {
            background: #2e6da4;
        }

        #question_background {
            border-radius: 5px;
            box-shadow: 4px;
            background: whitesmoke;
        }

        #question_title {
            font-size: 30px;
        }

        #top_label {
            font-family: 微软雅黑;
            font-size: 35px;
        }
    </style>
    <script>
        var json = ${questioncontent};
    </script>
</head>
<body>
<jsp:include page="/topbar.jsp"></jsp:include>
<div class="container" id="question_background">
    <div class="row">
        <img src="/source/Question_Lv3.svg" style="display: inline;width: 100px;height: 100px;">
        <label id="top_label">ISA Online Programing</label>
    </div>
    <HR style="FILTER: progid:DXImageTransform.Microsoft.Shadow(color:#987cb9,direction:145,strength:15)" width="80%"
        color=#987cb9 SIZE=1>
    <div class="col-md-offset-1 col-md-10">
        <div class="row">
            <label class="text-primary" id="question_title"></label>
        </div>
        <div class="row">
            <p class="text-warning" id="question_tip">
                TimeLimit:1000ms;
            </p>
        </div>
        <div class="row">
            <p class="text-info" id="question_content">

            </p>
        </div>
        <div class="row">
            <div class="col-md-9 col-xs-9">
                <textarea class="bg-info text-info" id="question_submit_content"
                          style="width: 100%; height:  120px; border-radius: 5px"></textarea>
            </div>
            <div class="col-md-3 col-xs-3" id="question_submit">
                <img src='/source/submit.svg' style='height: 80px;cursor: pointer'>
            </div>
        </div>
        <div class="row">
            <div class="alert alert-warning" style="display: none" id="question_info">

            </div>
        </div>
        <br>
        <br>
    </div>
</div>
<script src="/js/question.js"></script>
</body>
</html>
