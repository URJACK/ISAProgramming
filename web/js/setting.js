/**
 * Created by FuFangzhou on 2017/6/5.
 */
$(function () {
    var needChangePassword = false;
    var auto_LoginFailed = function () {
        alert("你的账户信息不符合");
        window.location = "/home";
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
    setTimeout(function () {
        if (!auto_login) {
            auto_LoginFailed();
        } else {
            auto_LoginSuccess();
        }
    },2500);        //登陆初始调用的方法
});