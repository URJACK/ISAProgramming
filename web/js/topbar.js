/** jsp 需要的代码
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
 <li class="navbar-btn"><img id="navright_hidden_img" src="/img/user"></li>
 <li><a id="navright_hidden_name"></a></li>
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
 */
$(function () {
    var receivemail_up = false;
    var receivemail_in = false;
    var warning_upcontent = "<div class='alert alert-warning'> <strong id='signup_warning_content'> </strong> </div>";
    var warning_incontent = "<div class='alert alert-warning'> <strong id='signin_warning_content'> </strong> </div>";
    // If the successful send validation code is to the mailbox, the method is executed to change the view
    var changeSigninForCaptcha = function () {
        var signin_captcha = document.getElementById('signin_checknumber');
        signin_captcha.style.display = 'block';
        $('#signin_account').get(0).setAttribute('disabled', '1');
        var signin_btn = document.getElementById('signin_btn');
        signin_btn.innerHTML = 'Confirm Captcha';
        var signin_confirm = document.getElementById('signin_confirm');
        signin_confirm.parentNode.style.display = 'block';
        var signin_password = document.getElementById('signin_password');
        signin_password.parentNode.children[0].innerHTML = "New Password";
    };
    // When the registration is successful, the method changes the view
    var changeSignUpForSuccess = function () {
        var signup_checknumber = document.getElementById('signup_checknumber');
        signup_checknumber.parentNode.style.display = 'block';
        var signup_btn = document.getElementById('signup_btn');
        signup_btn.innerHTML = "Finally Check";
        $('#signup_email').get(0).setAttribute('disabled', '1');
        $('#signup_password').get(0).setAttribute('disabled', '1');
        $('#signup_account').get(0).setAttribute('disabled', '1');
        $('#signup_confirm').get(0).setAttribute('disabled', '1');
    };
    // Sets the text of the registration box
    var setSignupWarning = function (str) {
        var warning = document.getElementById('signup_warning');
        warning.innerHTML = warning_upcontent;
        var warning_content = document.getElementById('signup_warning_content');
        warning_content.innerHTML = str;
    };
    // Sets the text of the login box
    var setSigninWarning = function (str) {
        var warning = document.getElementById('signin_warning');
        warning.innerHTML = warning_incontent;
        var warning_content = document.getElementById('signin_warning_content');
        warning_content.innerHTML = str;
    }
    // When this page is loaded, the method is executed to change the view
    var afterSuccessSignin = function (account) {
        $('#navright').css('display', 'none');
        $('#navright_hidden').css('display', 'block');
        $('#navright_hidden_img').get(0).src = "/img/user";
        $('#navright_hidden_name').get(0).innerHTML = account;
    };
    $('#signin_forget').click(function () {
        if (!receivemail_in) {
            setSigninWarning("正在向服务器确认");
            $.ajax({
                url: "/home/forget",
                type: "POST",
                data: {
                    account: $('#signin_account').val()
                },
                success: function (json) {
                    json = JSON.parse(json);
                    setSigninWarning(json.infos);
                    if (json.status) {
                        changeSigninForCaptcha();
                        receivemail_in = true;
                    }
                }
            });
        } else {
            setSigninWarning("请输入正确的验证码和新密码");
        }
    });
    $('#signup_btn').click(function () {
        if (!receivemail_up) {
            var reg_account = /^[a-zA-Z]\w{0,19}$/;
            if (!reg_account.test($('#signup_account').val())) {
                setSignupWarning("你的账号格式有误");
                return;
            }
            var reg_password = /^[a-zA-Z]\w{5,14}$/;
            if (!reg_password.test($('#signup_password').val())) {
                setSignupWarning("你的密码格式有误");
                return;
            }
            if ($('#signup_password').val() != $('#signup_confirm').val()) {
                setSignupWarning("你两次输入的密码不一致");
                return;
            }
            var reg_email = /^\w+@[a-z0-9]+\.[a-z]+$/;
            if (!reg_email.test($('#signup_email').val())) {
                setSignupWarning("你输入的邮箱格式有误");
                return;
            }
            setSignupWarning("正在注册你的账户");
            $.ajax({
                url: "/home/logup",
                type: "POST",
                // data: "account="+$('#signup_account').val()+"&password="+$('#signup_password').val()+"&email="+$('#signup_email').val(),
                data: {
                    account: $('#signup_account').val(),
                    email: $('#signup_email').val()
                },
                success: function (json) {   //json: Info_Status==true : 已经发送验证码  ; false: 尚未成功
                    json = JSON.parse(json);
                    setSignupWarning(json.infos);
                    if (json.status == true) {
                        changeSignUpForSuccess();
                        receivemail_up = true;
                    }
                }
            });
        }
        else {     //确认验证码
            $.ajax({
                url: "/home/logup2",
                type: "POST",
                data: {
                    account: $('#signup_account').val(),
                    password: $('#signup_password').val(),
                    email: $('#signup_email').val(),
                    checknumber: $('#signup_checknumber').val()
                },
                success: function (json) {
                    json = JSON.parse(json);
                    setSignupWarning(json.infos);
                    if (json.status) {
                        $('#navright_signup_modal').modal('hide');
                    }
                }
            })
        }
    });
    $('#signin_btn').click(function () {
        if (!receivemail_in) {               //登陆
            setSigninWarning("正在尝试登陆")
            $.ajax({
                url: "/home/login",
                type: "POST",
                data: {
                    account: $('#signin_account').val(),
                    password: $('#signin_password').val()
                },
                success: function (json) {
                    json = JSON.parse(json);
                    setSigninWarning(json.infos);
                    if (json.status) {
                        var account = "正在读取名字";
                        $.ajax({
                            url:"/home/getname",
                            type:"POST",
                            success:function (json) {
                                json = JSON.parse(json);
                                account = json.infos;
                            }
                        });
                        setTimeout(function () {
                            $('#navright_signin_modal').modal('hide');
                            afterSuccessSignin(account);
                        }, 1000);
                    }
                }
            })
        } else {                              //更改密码
            if ($('#signin_password').val() != $('#signin_confirm').val()) {
                setSigninWarning("两次新密码输入不一致");
                return;
            }
            setSigninWarning("正在尝试更改密码");
            $.ajax({
                url: "/home/forget2",
                type: "POST",
                data: {
                    account: $('#signin_account').val(),
                    password: $('#signin_password').val(),
                    checknumber: $('#signin_checknumber_content').val()
                },
                success: function (json) {
                    json = JSON.parse(json);
                    setSigninWarning(json.infos);
                    if (json.status) {
                        setTimeout(function () {
                            window.location = "/home";
                        }, 1000);
                    }
                }
            });
        }
    });
    $.ajax({
        url: "/home/defaultlogin",
        type: "POST",
        success: function (json) {
            var account = "正在读取名字";
            json = JSON.parse(json);
            console.log(json.infos);
            if (json.status) {
                $.ajax({
                    url:"/home/getname",
                    type:"POST",
                    success:function (json) {
                        json = JSON.parse(json);
                        account = json.infos;
                        afterSuccessSignin(account);
                    }
                });
            }
        }
    })
});