<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>ISA Programing</title>
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
    <style>
        td,th{
            text-align: center;
        }
    </style>
</head>
<body>
<jsp:include page="/topbar.jsp"/>
<script type="text/javascript" src="/js/setting.js"></script>
<div class="container" id=>
    <ul id="main_tab" class="nav nav-tabs">
        <li class="active"><a href="#main_tab_info" data-toggle="tab">
            基本信息</a>
        </li>
        <li><a href="#main_tab_moreinfo" data-toggle="tab">详细信息</a></li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle"
               data-toggle="dropdown">More<b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li><a href="#main_tab_topic" data-toggle="tab">
                    发表的帖子</a>
                </li>
                <li class="divider"></li>
                <li><a href="#main_tab_question" data-toggle="tab">
                    完成的题目</a>
                </li>
                <li class="divider"></li>
                <li><a href="#main_tab_friend" data-toggle="tab">
                    我的好友</a>
                </li>
            </ul>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="main_tab_info">
            <form>
                <div class="form-group">
                    <label>Account</label>
                    <label id="main_tab_info_account" class="form-control">正在获取账号</label>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <label id="main_tab_info_email" class="form-control">正在获取邮箱</label>
                </div>
                <div class="form-group">
                    <label>Sign Up Date</label>
                    <label id="main_tab_info_date" class="form-control">正在获取创建的日期</label>
                </div>
                <div class="form-group">
                    <a class="btn btn-primary" id="main_tab_info_changepassword">修改密码</a>
                </div>
                <div class="form-group" style="display: none">
                    <label>Old Password</label>
                    <input type="password" placeholder="请输入旧密码" class="form-control" id="main_tab_info_oldpassword">
                </div>
                <div class="form-group" style="display: none">
                    <label>New Password</label>
                    <input type="password" placeholder="请输入新密码" class="form-control" id="main_tab_info_newpassword">
                </div>
                <div class="form-group" style="display: none">
                    <label>Confirm Password</label>
                    <input type="password" placeholder="确认新密码" class="form-control" id="main_tab_info_confirmpassword">
                </div>
                <div class="form-group">
                    <div class="alert alert-warning">
                        <strong id="main_tab_info_content"></strong>
                    </div>
                </div>
                <div class="form-group" style="display: none">
                    <a class="btn btn-success" id="main_tab_info_commit">确认修改</a>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="main_tab_moreinfo">
            <form>
                <div class="form-group">
                    <label>Introduce</label>
                    <textarea id="main_tab_moreinfo_introduce" class="form-control" readonly>正在获取自我介绍</textarea>
                </div>
                <div class="form-group">
                    <label>Major</label>
                    <input type="text" id="main_tab_moreinfo_major" class="form-control" readonly value="正在获取主修">
                </div>
                <div class="form-group">
                    <label>class</label>
                    <input type="text" id="main_tab_moreinfo_class" class="form-control" readonly value="正在获取班级">
                </div>
                <div class="form-group">
                    <div class="alert alert-warning">
                        <strong id="main_tab_moreinfo_content"></strong>
                    </div>
                </div>
                <div class="form-group">
                    <a class="btn btn-primary" id="main_tab_moreinfo_commit">点击更改</a>
                </div>
            </form>
        </div>
        <div class="tab-pane fade " id="main_tab_topic">
            <p>jMeter is an Open Source testing software. It is 100% pure
                Java application for load and performance testing.</p>
        </div>
        <div class="tab-pane fade" id="main_tab_question">
            <p>Enterprise Java Beans (EJB) is a development architecture
                for building highly scalable and robust enterprise level
                applications to be deployed on J2EE compliant
                Application Server such as JBOSS, Web Logic etc.
            </p>
        </div>
        <div class="tab-pane fade" id="main_tab_friend">
            <div class="row">
                <div class="col-md-5 col-xs-12">
                    <table class="table table-striped">
                        <caption>你的好友</caption>
                        <thead>
                        <tr>
                            <th>Account</th>
                            <th>Email</th>
                            <th>Operation</th>
                        </tr>
                        </thead>
                        <tbody id="main_tab_friend_tbody">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("[clk='']").click(function () {
        var counter = 0;
        console.log("CLK Ok"+counter++);
    });
</script>
</body>
</html>