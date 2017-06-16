<%--
  Created by IntelliJ IDEA.
  User: FuFangzhou
  Date: 2017/6/16
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify Question Information</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
    <script>
        var json = ${model};
        console.log(json);
    </script>
</head>
<body>
<jsp:include page="/topbar.jsp"/>
<div class="container">
    <form>
        <div class="form-group">
            <label>Id</label>
            <input type="number" class="form-control" id="modify_question_id" readonly>
        </div>
        <div class="form-group">
            <label>LV</label>
            <input type="number" class="form-control" id="modify_question_lv">
        </div>
        <div class="form-group">
            <label>Number</label>
            <input type="number" class="form-control" id="modify_question_number">
        </div>
        <div class="form-group">
            <label>Title</label>
            <input type="text" class="form-control" id="modify_question_title" value="正在获取标题">
        </div>
        <div class="form-group">
            <label>Content</label>
            <input type="text" class="form-control" id="modify_question_content" value="正在获取内容">
        </div>
        <div class="form-group">
            <label>Tip</label>
            <input type="text" class="form-control" id="modify_question_tip" value="正在获取提示">
        </div>
        <div class="form-group">
            <a href="#" id="modify_reset" class="btn btn-primary">还原次新进度</a>
            <a href="#" id="modify_commit" class="btn btn-success">同步最新进度</a>
        </div>
        <div class="form-group">
            <div class="alert alert-warning" id="modify_warning">

            </div>
        </div>
    </form>
</div>
<script src="/js/manage_modify_question.js"></script>
</body>
</html>
