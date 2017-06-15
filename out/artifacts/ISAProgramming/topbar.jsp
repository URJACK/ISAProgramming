<div class="container" id="topbar" style="width: 100%">
    <div class="row">
        <div class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand" id="navbar_brand">SICNU</a>
                    <button class="navbar-toggle" data-toggle="collapse" data-target="#navcallapse">
                        <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="navcallapse">
                    <ul class="nav navbar-nav" id="navleft">
                        <li id="navleft_isa"><a href="#">ISA</a></li>
                        <li id="navleft_program"><a href="#">Program</a></li>
                        <li id="navleft_eda"><a href="#">EDA</a></li>
                        <li id="navleft_manage"><a href="#"
                        data-toggle="modal" data-target="#navright_admin">Manage</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" id="navright">
                        <a id="navright_signin" class="btn btn-primary navbar-btn" data-toggle='modal'
                           data-target='#navright_signin_modal'>Sign In</a>
                        <a id="navright_signup" class="btn btn-info navbar-btn" data-toggle="modal"
                           data-target="#navright_signup_modal">Sign Up</a>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" id="navright_hidden" style="display: none;">
                        <li class="navbar-btn"><img class="img-circle" id="navright_hidden_img" style="width: 30px;height: 30px"></li>
                        <li class="dropdown">
                            <a href="#" id="navright_hidden_name" class="dropdown-toggle" data-toggle="dropdown"></a>
                            <ul class="dropdown-menu">
                                <li><a class="text-primary" href="#" id="navright_hidden_name_setting">About MySelf</a></li>
                                <li class="divider"></li>
                                <li><a class="text-primary" href="#" id="navright_hidden_name_logout">Log Out</a></li>
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
                <h4 class="modal-title">SIGN IN</h4></div>
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
                <h4 class="modal-title">SIGN UP</h4></div>
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
<div class="modal fade" id="navright_admin" tabindex="-1" role='dialog'>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">ADMIN CHECK</h4></div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" placeholder="Please Input Your Password" class="form-control" id="admin_password">
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="admin_btn">CHECK NOW</button>
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