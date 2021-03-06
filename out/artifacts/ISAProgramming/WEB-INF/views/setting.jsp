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
        td, th {
            text-align: center;
        }

        #main_tab_friend_tbody img {
            width: 22px;
            height: 22px;
        }

        display {
            font-family: "微软雅黑";
        }
    </style>
</head>
<body>
<jsp:include page="/topbar.jsp"/>
<script type="text/javascript" src="/js/setting.js"></script>
<div class="container" style="width: 100%">
    <div class="col-md-offset-2 col-md-8">
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
                    <li><a href="#main_tab_topic" data-toggle="tab" id="dropdown_topic">
                        发表的帖子</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="#main_tab_question" data-toggle="tab">
                        完成的题目</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="#main_tab_friend" data-toggle="tab" id="dropdown_friend">
                        我的好友</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="#main_tab_request" data-toggle="tab" id="dropdown_request_friend">
                        好友申请</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="main_tab_info">
            <div class="col-md-8 col-xs-12 col-md-offset-2">
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
                        <input type="password" placeholder="确认新密码" class="form-control"
                               id="main_tab_info_confirmpassword">
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
        </div>
        <div class="tab-pane fade" id="main_tab_moreinfo">
            <div class="col-md-8 col-xs-12 col-md-offset-2">
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
                        <input type="number" id="main_tab_moreinfo_class" class="form-control" readonly value="正在获取班级">
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
        </div>
        <div class="tab-pane fade " id="main_tab_topic">
            <div class="col-md-8 col-xs-12 col-md-offset-2">
                <table class="table table-bordered table-hover">
                    <caption>你发表过的帖子</caption>
                    <thead>
                    <tr>
                        <th style="display: none;">id</th>
                        <th>Title</th>
                        <th>Owner</th>
                    </tr>
                    </thead>
                    <tbody id="main_tab_topic_tbody">

                    </tbody>
                </table>
            </div>
        </div>
        <div class="tab-pane fade" id="main_tab_question">
            <div class="col-md-8 col-xs-12 col-md-offset-2">
                <table class="table table-bordered table-hover">
                    <caption><label class="text-primary" style="font-size: 20px;font-family: 微软雅黑">你已经完成的题目</label>
                    </caption>
                    <thead>
                    <tr>
                        <th>难度</th>
                        <th>题号</th>
                        <th>题目名</th>
                        <th>完成时间</th>
                    </tr>
                    </thead>
                    <tbody id="main_tab_question_tbody">

                    </tbody>
                </table>
            </div>
        </div>
        <div class="tab-pane fade" id="main_tab_friend">
            <div class="row">
                <div class="col-md-5 col-xs-12">
                    <table class="table table-striped">
                        <caption>
                            好友列表
                        </caption>
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
                    <div class="input-group">
                        <input type="text" class="form-control" id="main_tab_friend_add_content">
                        <div class="input-group-btn" id="main_tab_friend_add">
                            <a class="btn btn-primary">添加好友</a>
                        </div>
                    </div>
                    <div class="alert alert-warning">
                        <strong id="main_tab_friend_add_warning"></strong>
                    </div>
                </div>
                <div class="col-md-7 col-xs-12" id="main_tab_friend_display">

                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="main_tab_request">
            <div class="col-md-offset-2 col-md-8 col-xs-12">
                <table class="table table-striped">
                    <caption><label class="text-primary">Friend Notice</label></caption>
                    <thead>
                    <tr>
                        <th>Account</th>
                        <th>Email</th>
                        <th>Operation</th>
                    </tr>
                    </thead>
                    <tbody id="main_tab_request_tbody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>