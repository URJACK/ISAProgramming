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
</head>
<body>
<div class="container" id="topbar" style="width: 100%">
    <div class="row">
        <div class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand">ISA</a>
                    <button class="navbar-toggle" data-toggle="collapse" data-target="#navcallapse">
                        <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="navcallapse">
                    <ul class="nav navbar-nav" id="navleft">
                        <li id="navleft_homepage"><a href="#">HomePage</a></li>
                        <li id="navleft_program"><a href="#">Program</a></li>
                        <li id="navleft_menber"><a href="#">Menber</a></li>
                        <li id="navleft_information"><a href="#">Information</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" id="navright">
                        <a id="navright_signin" class="btn btn-primary navbar-btn" data-toggle='modal'
                           data-target='#navright_signin_modal'>Sign In</a>
                        <a id="navright_signup" class="btn btn-info navbar-btn" data-toggle="modal"
                           data-target="#navright_signup_modal">Sign Up</a>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" id="navright_hidden" style="display: none;">
                        <li class="navbar-btn"><img class="img-circle" id="navright_hidden_img"></li>
                        <li class="dropdown">
                            <a href="#" id="navright_hidden_name" class="dropdown-toggle" data-toggle="dropdown"></a>
                            <ul class="dropdown-menu">
                                <li><a class="text-primary" href="#" id="navright_hidden_name_setting">setting</a></li>
                                <li class="divider"></li>
                                <li><a class="text-primary" href="#" id="navright_hidden_name_logout">log out</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="navright_signin_modal" tabindex="-1" role='dialog'>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">账户登陆</h4></div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form>
                        <div class="form-group">
                            <label>Account</label>
                            <input type="text" placeholder="Please Input Your Account" name="account"
                                   class="form-control" id="signin_account">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" placeholder="Please Input Your Password" name="password"
                                   class="form-control" id="signin_password">
                        </div>
                        <div class="form-group" style="display: none">
                            <label>ConfirmPassword</label>
                            <input type="password" placeholder="Please Input Your Password" name="password"
                                   class="form-control" id="signin_confirm">
                        </div>
                        <div class="form-group">
                            <a class="btn btn-primary" title="忘记密码" data-toggle="popover" data-trigger="hover"
                               data-placement="right"
                               data-content="点击该按钮从而我们会尝试着向你的邮箱里发送了验证邮件(十分钟内不会重复发送):在你的邮箱里，会收到该有的验证邮件，得到验证码并输入它!"
                               id="signin_forget">Forget Password?</a>
                        </div>
                        <div class="form-group" style="display: none" id="signin_checknumber">
                            <label>CAPTCHA</label>
                            <input type="text" placeholder="Please Input Your CAPTCHA" name="captcha"
                                   class="form-control" id="signin_checknumber_content">
                        </div>
                        <div class="form-group" id="signin_warning">

                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="signin_btn">Sign in</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="navright_signup_modal" tabindex="-1" role='dialog'>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">账户注册</h4></div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form>
                        <div class="form-group">
                            <label>Account</label>
                            <input type="text" placeholder="Please Input Your Account" name="account"
                                   class="form-control" id="signup_account">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" placeholder="Please Input Your Password" name="password"
                                   class="form-control" id="signup_password">
                        </div>
                        <div class="form-group">
                            <label>Confirm Password</label>
                            <input type="password" placeholder="Please Confirm Your Password" name="password"
                                   class="form-control" id="signup_confirm">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="text" id="signup_email" placeholder="Please Input Your Email" name="email"
                                   class="form-control">
                        </div>
                        <div class="form-group" id="signup_warning">

                        </div>
                        <div class="form-group" style="display: none">
                            <label>CheckNumber</label>
                            <input type="text" id="signup_checknumber" placeholder="The Checknumber is in your Email"
                                   name="checknumber" class="form-control">
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="signup_btn">Sign up</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("[data-toggle='popover']").popover();
    })
</script>
<script type="text/javascript" src="/js/topbar.js"></script>
<script type="text/javascript" src="/js/setting.js"></script>
<div class="container" id="main">
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
                <li><a href="#main_tab_topic"  data-toggle="tab">
                    发表的帖子</a>
                </li>
                <li class="divider"></li>
                <li><a href="#main_tab_question" data-toggle="tab">
                    完成的题目</a>
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
                    <input type="text" placeholder="请输入旧密码" class="form-control" id="main_tab_info_oldpassword">
                </div>
                <div class="form-group" style="display: none">
                    <label>New Password</label>
                    <input type="text" placeholder="请输入新密码" class="form-control" id="main_tab_info_newpassword">
                </div>
                <div class="form-group" style="display: none">
                    <label>Confirm Password</label>
                    <input type="text" placeholder="确认新密码" class="form-control" id="main_tab_info_confirmpassword">
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
        <div class="tab-pane fade " id="main_tab_moreinfo">
            <p>iOS is a mobile operating system developed and distributed by Apple
                Inc. Originally released in 2007 for the iPhone, iPod Touch, and
                Apple TV. iOS is derived from OS X, with which it shares the
                Darwin foundation. iOS is Apple's mobile version of the
                OS X operating system used on Apple computers.</p>
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
    </div>
</div>
</body>
</html>