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
    var afterSuccessSignin = function (id) {
        $('#navright').css('display', 'none');
        $('#navright_hidden').css('display', 'block');
        $('#navright_hidden_img').get(0).src="/img/user?id="+id;
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
                success:function (json) {
                    json = JSON.parse(json);
                    setSigninWarning(json.infos);
                    if (json.status){
                        setTimeout(function () {
                            $('#navright_signin_modal').modal('hide');
                            afterSuccessSignin(json.id);
                        },1000);
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
        url:"/home/defaultlogin",
        type:"POST",
        success:function (json) {
            json = JSON.parse(json);
            console.log(json.infos);
            if (json.status){
                setTimeout(function () {
                    afterSuccessSignin(json.id);
                })
            }
        }
    })
});