/**
 * Created by FuFangzhou on 2017/6/5.
 */
$(function () {
    var needChangePassword = false;
    var needChangeMoreInformation = false;
    var firendslist;
    //自动登陆如果失败了
    var auto_LoginFailed = function () {
        alert("你的账户信息不符合");
        window.location = "/home";
    };
    //在基本信息界面的信息提示框
    var setTabInfoContent = function (str) {
        $('#main_tab_info_content').get(0).innerHTML = str;
    };
    //在详细信息界面的信息提示框
    var setTabMoreInfoContent = function (str) {
        $('#main_tab_moreinfo_content').get(0).innerHTML = str;
    };
    //清除Friend 界面右边的内容
    var clearElement_FriendContent = function () {
        var display = $('#main_tab_friend_display').get(0);
        var arr = display.childNodes;
        while (arr.length != 0)
            display.removeChild(arr[0]);
    }
    //在朋友界面点击查询后 根据传入的Json 设置Friend 界面的右边的内容
    var resetElementInFriendContent = function (obj) {
        clearElement_FriendContent();

        var display = $('#main_tab_friend_display').get(0);

        var oForm = document.createElement('form');
        var oAccount = document.createElement('div');
        var oEmail = document.createElement('div');
        var oMajor = document.createElement('div');
        var oDate = document.createElement('div');
        var oClass = document.createElement('div');

        var oAccount_label = document.createElement('label');
        var oEmail_label = document.createElement('label');
        var oMajor_label = document.createElement('label');
        var oDate_label = document.createElement('label');
        var oClass_label = document.createElement('label');

        var oAccount_display = document.createElement('display');
        var oEmail_display = document.createElement('display');
        var oMajor_display = document.createElement('display');
        var oDate_display = document.createElement('display');
        var oClass_display = document.createElement('display');

        oAccount_label.innerHTML = "Account";
        oEmail_label.innerHTML = "Email";
        oMajor_label.innerHTML = "Major";
        oDate_label.innerHTML = "Date";
        oClass_label.innerHTML = "Class";

        oAccount_display.innerHTML = obj.account;
        oEmail_display.innerHTML = obj.email;
        oMajor_display.innerHTML = obj.major;
        oDate_display.innerHTML = obj.date;
        oClass_display.innerHTML = obj.clazz;

        $(oAccount_display).addClass('form-control');
        $(oEmail_display).addClass('form-control');
        $(oMajor_display).addClass('form-control');
        $(oDate_display).addClass('form-control');
        $(oClass_display).addClass('form-control');

        oAccount.appendChild(oAccount_label);
        oAccount.appendChild(oAccount_display);
        oEmail.appendChild(oEmail_label);
        oEmail.appendChild(oEmail_display);
        oMajor.appendChild(oMajor_label);
        oMajor.appendChild(oMajor_display);
        oDate.appendChild(oDate_label);
        oDate.appendChild(oDate_display);
        oClass.appendChild(oClass_label);
        oClass.appendChild(oClass_display);

        oForm.appendChild(oAccount);
        oForm.appendChild(oEmail);
        oForm.appendChild(oMajor);
        oForm.appendChild(oDate);
        oForm.appendChild(oClass);

        display.appendChild(oForm);
    };
    //在朋友界面点击查询
    var friend_query = function () {
        //目标的Account
        var targetAccount = this.parentNode.parentNode.parentNode.children[0].innerHTML;
        $.ajax({
            url: "/model/otheruser",
            type: "POST",
            data: {
                account: targetAccount
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    resetElementInFriendContent(json.obj);
                }
            }
        })
    };
    //在朋友界面点击删除
    var friend_delete = function () {
        console.log("delete");
    };
    //在朋友界面点击聊天
    var friend_chat = function () {
        console.log("chat");
    };
    //刷新朋友界面的列表
    var refreshFirendsList = function (friends) {
        var tbody = $('#main_tab_friend_tbody').get(0);
        var arr = tbody.getElementsByTagName('tr');
        while (arr.length != 0)
            tbody.removeChild(arr[0]);

        for (var i = 0; i < friends.length; i++) {
            var child = document.createElement('tr');
            var oAccount = document.createElement('td');
            var oEmail = document.createElement('td');
            var oPerate = document.createElement('td');
            var oPerate_Div = document.createElement('div');
            var oQuery = document.createElement('a');
            var oDelete = document.createElement('a');
            var oChat = document.createElement('a');

            oQuery.innerHTML = "Query";
            oDelete.innerHTML = "Delete";
            oChat.innerHTML = "Chat";
            oQuery.href = '#';
            oDelete.href = '#';
            oChat.href = '#';
            var jqoQuery = $(oQuery);
            var jqoDelete = $(oDelete);
            var jqoChat = $(oChat);
            var jqoPerate_Div = $(oPerate_Div);
            jqoPerate_Div.addClass('btn-group');
            jqoPerate_Div.addClass('btn-group-sm');
            jqoQuery.addClass('btn');
            jqoDelete.addClass('btn');
            jqoChat.addClass('btn');
            jqoQuery.addClass('btn btn-default');
            jqoDelete.addClass('btn btn-default');
            jqoChat.addClass('btn btn-default');
            jqoQuery.attr('clk', '1');
            jqoChat.attr('clk', '2');
            jqoDelete.attr('clk', '3');

            oPerate_Div.appendChild(oQuery);
            oPerate_Div.appendChild(oDelete);
            oPerate_Div.appendChild(oChat);

            oPerate.appendChild(oPerate_Div);
            oEmail.innerHTML = friends[i].email;
            oAccount.innerHTML = friends[i].account;
            child.appendChild(oAccount);
            child.appendChild(oEmail);
            child.appendChild(oPerate);
            tbody.appendChild(child);
        }
        $("[clk='1']").click(friend_query);
        $("[clk='2']").click(friend_chat);
        $("[clk='3']").click(friend_delete);
    };
    //自动登陆成功成功了
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
        });         //得到User 的个人信息
        $.ajax({
            url: '/model/friend',
            data: {
                account: account
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                firendslist = json.obj;
                if (json.status) {
                    refreshFirendsList(firendslist);
                }
            }
        })
    };
    //通过点击修改按钮，改变基本信息界面的状态
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
    //通过点击修改按钮，改变详细信息界面的状态
    var changeMoreInformation = function () {
        if (!needChangeMoreInformation) {
            setTabMoreInfoContent("请开始修改你的信息");
            $('#main_tab_moreinfo_introduce').removeAttr('readonly');
            $('#main_tab_moreinfo_class').removeAttr('readonly');
            $('#main_tab_moreinfo_major').removeAttr('readonly');
            needChangeMoreInformation = true;
        } else {
            $('#main_tab_moreinfo_introduce').attr('readonly', '');
            $('#main_tab_moreinfo_class').attr('readonly', '');
            $('#main_tab_moreinfo_major').attr('readonly', '');
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
        if (needChangeMoreInformation) {
            $.ajax({
                url: "/setting/moreinfo",
                type: "POST",
                data: {
                    account: account,
                    introduce: $('#main_tab_moreinfo_introduce').val(),
                    major: $('#main_tab_moreinfo_major').val(),
                    clazz: $('#main_tab_moreinfo_class').val()
                },
                success: function (json) {
                    json = JSON.parse(json);
                    setTabMoreInfoContent(json.infos);
                    if (json.status) {
                        changeMoreInformation();
                    }
                }
            })
        } else
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