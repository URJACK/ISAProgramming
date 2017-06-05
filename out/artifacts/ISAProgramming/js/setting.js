/**
 * Created by FuFangzhou on 2017/6/5.
 */
$(function () {
    var needChangePassword = false;
    var auto_LoginFailed = function () {
        alert("你的账户信息不符合");
        window.location = "/home";
    };
    var setTabInfoContent = function(str){
        $('#main_tab_info_content').get(0).innerHTML = str;
    };
    var auto_LoginSuccess = function () {
        $.ajax({
            url:'/model/user',
            data:{
                account:account
            },
            success:function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status){
                    $('#main_tab_info_account').get(0).innerHTML = json.account;
                    $('#main_tab_info_email').get(0).innerHTML = json.email;
                    $('#main_tab_info_date').get(0).innerHTML = json.date;
                }
            }
        })
    };
    $('#main_tab_info_changepassword').click(function () {
        if (needChangePassword == false) {
            $('#main_tab_info_oldpassword').get(0).parentNode.style.display='block';
            $('#main_tab_info_newpassword').get(0).parentNode.style.display='block';
            $('#main_tab_info_confirmpassword').get(0).parentNode.style.display='block';
            $('#main_tab_info_commit').get(0).parentNode.style.display='block';
            needChangePassword = true;
        }else {
            $('#main_tab_info_oldpassword').get(0).parentNode.style.display='none';
            $('#main_tab_info_newpassword').get(0).parentNode.style.display='none';
            $('#main_tab_info_confirmpassword').get(0).parentNode.style.display='none';
            $('#main_tab_info_commit').get(0).parentNode.style.display='none';
            needChangePassword = false;
        }
    });
    $('#main_tab_info_commit').click(function () {
        var newpassword = $('#main_tab_info_newpassword').val();
        var oldpassword = $('#main_tab_info_oldpassword').val();
        var confirmpassword = $('#main_tab_info_confirmpassword').val();
        var passwordreg = /^[a-zA-Z]\w{2,15}$/;
        if (newpassword != confirmpassword){
            setTabInfoContent("你输入的密码两次不一样");
            return;
        }
        if(!passwordreg.test(newpassword)){
            setTabInfoContent("密码必须首字符为字母，之后跟上2~15个数字字母下划线");
            return;
        }
        if(newpassword == oldpassword){
            setTabInfoContent("你的新密码与旧密码相同");
            return;
        }

        $.ajax({
            url:"/setting/password",
            data:{
                account:account,
                newpassword:$('#main_tab_info_newpassword').val()
            },
            success:function (json) {
                json = JSON.parse(json);
                setTabInfoContent(json.infos);

            }
        })
    });
    setTimeout(function () {
        if (!auto_login) {
            auto_LoginFailed();
        } else {
            auto_LoginSuccess();
        }
    },2500);        //登陆初始调用的方法
});