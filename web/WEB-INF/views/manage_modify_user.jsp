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
    <title>Modify User Information</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
    <script>
        var json = ${model};
    </script>
</head>
<body>
<jsp:include page="/topbar.jsp"/>
<div class="container">
    <form>
        <div class="form-group">
            <label>Id</label>
            <input type="number" class="form-control" id="modify_user_id" readonly>
        </div>
        <div class="form-group">
            <label>Class</label>
            <input type="number" class="form-control" id="modify_user_class">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="text" class="form-control" id="modify_user_password" value="正在获取密码">
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="text" class="form-control" id="modify_user_email" value="正在获取邮箱">
        </div>
        <div class="form-group">
            <label>Account</label>
            <input type="text" class="form-control" id="modify_user_account" value="正在获取账号">
        </div>
        <div class="form-group">
            <label>Date</label>
            <input type="text" class="form-control" id="modify_user_date" value="正在获取日期" readonly>
        </div>
        <div class="form-group">
            <a href="#" id="modify_user_reset" class="btn btn-primary">还原次新进度</a>
            <a href="#" id="modify_user_commit" class="btn btn-success">同步最新进度</a>
        </div>
        <div class="form-group">
            <div class="alert alert-warning" id="modify_warning">

            </div>
        </div>
    </form>
</div>
<script src="/js/manage_modify_user.js"></script>
</body>
</html>
