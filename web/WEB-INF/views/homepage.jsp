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
<div class="container" id="container" style="width: 100%">
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
                        <img id="navright_hidden_img">
                        <li><a id="navright_hidden_name"></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="row" id="main">
        <div class="col-md-12 column">
            <div class="jumbotron col-md-8 col-md-offset-2 col-xs-12">
                <h1>ISA编程测评系统</h1>
                <h2>协会成立初衷</h2>
                <p>
                    ·同学你好，在这个信息大爆炸的网络时代，信息安全成为人们关注的焦点，作为当代大学生，先进知识分子，我们有责任和义务结合我们所学的专业知识向广大群众普及网络信息安全知识，帮助大家解决网络和信息安全问题。<br>
                    ·网络安全竞争日益激烈，我们急需一批热血青年来捍卫我们的网络安全，保卫我们的网络疆土。为选拔、培养、输送信息安全人才，在学校和学院领导,协会指导老师的帮助和指导下我们成立了信息安全协会。<br>
                </p>
                <h2>测评系统介绍</h2>
                <p>这是<ffz style="color: #333;font-size: 25px;font-family:
'微软雅黑'">信息安全协会</ffz>的在线编程测评系统。在这里，你可以和协会的其他成员一起提升自己的代码水平。<br>
                    我们目前已经支持以下语言:<br>
                    <button class="btn btn-default" style="color: red" font-family='微软雅黑' title="About Java" data-
                            container="body" data-toggle="popover" data-trigger='hover' data-placement='bottom'
                            data-content="Java是一门面向对象编程语言，不仅吸收了C++
语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极
好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程[1]  。">Java
                    </button>
                </p>
                <button class="btn btn-info col-md-offset-11 col-xs-offset-8"
                        id="main_learnmore">Learn More
                </button>
                <p style="text-align: center;font-size: 12px;font-family: '微软雅黑';color: blue;margin-top:30px">
                    四川师范大学--物理与电子工程学院</p>
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
                               data-placement="right" data-content="点击该按钮从而我们会尝试着向你的邮箱里发送了验证邮件(十分钟内不会重复发送):在你的邮箱里，会收到该有的验证邮件，得到验证码并输入它!"
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
                            <input type="text" id="signup_email" placeholder="Please Input Your Email" name="email" class="form-control">
                        </div>
                        <div class="form-group" id="signup_warning">

                        </div>
                        <div class="form-group" style="display: none">
                            <label>CheckNumber</label>
                            <input type="text" id="signup_checknumber" placeholder="The Checknumber is in your Email" name="checknumber" class="form-control">
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
<script type="text/javascript" src="/js/homepage.js"></script>
</body>
</html>