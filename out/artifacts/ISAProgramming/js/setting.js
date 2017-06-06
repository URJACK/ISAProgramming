/**
 * Created by FuFangzhou on 2017/6/5.
 */
$(function () {
    var needChangePassword = false;
    var needChangeMoreInformation = false;
    var auto_LoginFailed = function () {
        alert("你的账户信息不符合");
        window.location = "/home";
    };
    var setTabInfoContent = function (str) {
        $('#main_tab_info_content').get(0).innerHTML = str;
    };
    var setTabMoreInfoContent = function (str) {
        $('#main_tab_moreinfo_content').get(0).innerHTML = str;
    };
    var auto_LoginSuccess = function () {
        $.ajax({
            url: '/model/user',
            data: {
                account: account
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    $('#main_tab_info_account').get(0).innerHTML = json.account;
                    $('#main_tab_info_email').get(0).innerHTML = json.email;
                    $('#main_tab_info_date').get(0).innerHTML = json.date;
                    //More info
                    $('#main_tab_moreinfo_introduce').get(0).innerHTML = json.introduce;
                    $('#main_tab_moreinfo_class').get(0).value = json.clazz;
                    $('#main_tab_moreinfo_major').get(0).value = json.major;
                }
            }
        });
    };
    var changeModifyPasswordStatus = function () {
        if (needChangePassword == false) {
            $('#main_tab_info_oldpassword').get(0).parentNode.style.display = 'block';
            $('#main_tab_info_newpassword').get(0).parentNode.style.display = 'block';
            $('#main_tab_info_confirmpassword').get(0).parentNode.style.display = 'block';
            $('#main_tab_info_commit').get(0).parentNode.style.display = 'block';
            needChangePassword = true;
        } else {
            $('#main_tab_info_oldpassword').get(0).parentNode.style.display = 'none';
            $('#main_tab_info_newpassword').get(0).parentNode.style.display = 'none';
            $('#main_tab_info_confirmpassword').get(0).parentNode.style.display = 'none';
            $('#main_tab_info_commit').get(0).parentNode.style.display = 'none';
            needChangePassword = false;
        }
    };
    var changeMoreInformation = function () {
        if (!needChangeMoreInformation) {
            setTabMoreInfoContent("请开始修改你的信息");
            $('#main_tab_moreinfo_commit').removeClass('btn-primary');
            $('#main_tab_moreinfo_commit').addClass('btn-success');
            $('#main_tab_moreinfo_commit').get(0).innerHTML = "确认修改";
            $('#main_tab_moreinfo_introduce').removeAttr('readonly');
            $('#main_tab_moreinfo_class').removeAttr('readonly');
            $('#main_tab_moreinfo_major').removeAttr('readonly');
            needChangeMoreInformation = true;
        }else {
            $('#main_tab_moreinfo_commit').removeClass('btn-success');
            $('#main_tab_moreinfo_commit').addClass('btn-primary');
            $('#main_tab_moreinfo_commit').get(0).innerHTML = "再次修改";
            $('#main_tab_moreinfo_introduce').attr('readonly','');
            $('#main_tab_moreinfo_class').attr('readonly','');
            $('#main_tab_moreinfo_major').attr('readonly','');
            needChangeMoreInformation = false;
        }
    };
    $('#main_tab_info_changepassword').click(changeModifyPasswordStatus);
    $('#main_tab_info_commit').click(function () {
        var vnewpassword = $('#main_tab_info_newpassword').val();
        var voldpassword = $('#main_tab_info_oldpassword').val();
        var confirmpassword = $('#main_tab_info_confirmpassword').val();
        var passwordreg = /^[a-zA-Z]\w{2,15}$/;
        if (vnewpassword != confirmpassword) {
            setTabInfoContent("你输入的密码两次不一样");
            return;
        }
        if (!passwordreg.test(vnewpassword)) {
            setTabInfoContent("密码必须首字符为字母，之后跟上2~15个数字字母下划线");
            return;
        }
        if (vnewpassword == voldpassword) {
            setTabInfoContent("你的新密码与旧密码相同");
            return;
        }

        $.ajax({
            url: "/setting/password",
            data: {
                account: account,
                oldpassword: voldpassword,
                newpassword: vnewpassword
            },
            success: function (json) {
                json = JSON.parse(json);
                setTabInfoContent(json.infos);
                if (json.status) {
                    setTimeout(function () {
                        changeModifyPasswordStatus();
                        $('#main_tab_info_changepassword').get(0).parentNode.style.display = 'none';
                    }, 1000)
                }
            }
        })
    });
    $('#main_tab_moreinfo_commit').click(function () {
        if (needChangeMoreInformation){
            $.ajax({
                url:"/setting/moreinfo",
                data:{
                    account:account,
                    introduce:$('#main_tab_moreinfo_introduce').val(),
                    major:$('#main_tab_moreinfo_major').val(),
                    clazz:$('#main_tab_moreinfo_class').val()
                },
                success:function (json) {
                    json = JSON.parse(json);
                    setTabMoreInfoContent(json.infos);
                    if (json.status){
                        changeMoreInformation();
                    }
                }
            })
        }else
            changeMoreInformation();
    });
    setTimeout(function () {
        if (!auto_login) {
            auto_LoginFailed();
        } else {
            auto_LoginSuccess();
        }
    }, 2500);        //登陆初始调用的方法
});