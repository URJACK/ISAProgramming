/**
 * Created by FuFangzhou on 2017/6/5.
 */
$(function () {
    var needChangePassword = false;
    var needChangeMoreInformation = false;
    var targetAccount;                  //被当前用户选中要查看或者聊天的目标Account
    var firendslist;
    var chatIndex = 0;                      //记录当前聊天记录的条数
    var haveSolvedList;                     //已经解决的问题的

    var timer;

    //自动登陆如果失败了
    var auto_LoginFailed = function () {
        alert("你的账户信息不符合");
        window.location = "/home";
    };
    //自动登陆成功成功了
    var auto_LoginSuccess = function () {
        postGetUser();         //得到User 的个人信息
        postGetFriend();         //得到User 的朋友信息
        postGetRequestFriend();            //得到User 的申请列表的信息
        postGetHaveSolvedList();            //得到User 已经解决的列表的信息
    };
    //得到自己的信息
    var postGetUser = function () {
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
    //得到自己的朋友列表
    var postGetFriend = function () {
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
        });
    };
    //得到请求自己朋友的列表
    var postGetRequestFriend = function () {
        $.ajax({
            url: '/model/requestfriend',
            type: 'POST',
            data: {
                account: account
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    clearElement_FriendRequest();
                    resetElementInRequestContent(json.obj);
                }
            }
        });
    };
    //得到自己已经解决的问题的列表
    var postGetHaveSolvedList = function () {
        $.ajax({
            url: '/model/solvelist',
            type: 'POST',
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    haveSolvedList = json.obj;
                    clearElement_SolvedList();
                    resetElementInSolvedList();
                }
            }
        })
    };
    //清除Friend 界面右边的内容
    var clearElement_FriendContent = function () {
        var display = $('#main_tab_friend_display').get(0);
        var arr = display.childNodes;
        while (arr.length != 0)
            display.removeChild(arr[0]);
    };
    //清除申请列表界面里表格的所有的内容
    var clearElement_FriendRequest = function () {
        var oTbody = $('#main_tab_request_tbody').get(0);
        oTbody.innerHTML = '';
    };
    //清楚已经解决的问题列表的所有内容
    var clearElement_SolvedList = function () {
        var oTbody = $('#main_tab_question_tbody').get(0);
        oTbody.innerHTML = "";
    };
    //在申请列表界面里，根据传入的json 设置申请列表界面的内容
    var resetElementInRequestContent = function (objs) {
        var oTbody = $('#main_tab_request_tbody').get(0);
        for (var i = 0; i < objs.length; i++) {
            var oTr = document.createElement('tr');
            var oTd_Account = document.createElement('td');
            var oTd_Email = document.createElement('td');
            var oTd_Operate = document.createElement('td');
            var oDiv = document.createElement('div');
            var oA_Agree = document.createElement('a');
            var oA_Refuse = document.createElement('a');

            oTd_Account.innerHTML = objs[i].account;
            oTd_Email.innerHTML = objs[i].email;
            var jq_oDiv = $(oDiv);
            jq_oDiv.addClass('btn-group btn-group-sm');
            oA_Agree.setAttribute('class', 'btn btn-default');
            oA_Refuse.setAttribute('class', 'btn btn-default');
            oA_Agree.setAttribute('clk', 'request_tbody_agree');
            oA_Refuse.setAttribute('clk', 'request_tbody_refuse');
            oA_Agree.innerHTML = 'Agree';
            oA_Refuse.innerHTML = 'Refuse';
            oDiv.appendChild(oA_Agree);
            oDiv.appendChild(oA_Refuse);
            oTd_Operate.appendChild(oDiv);
            oTr.appendChild(oTd_Account);
            oTr.appendChild(oTd_Email);
            oTr.appendChild(oTd_Operate);

            oTbody.appendChild(oTr);
        }
        $("[clk='request_tbody_agree']").click(requestFriendAgree);
        $("[clk='request_tbody_refuse']").click(requestFriendRefuse);
    };
    //在朋友界面点击查询后 根据传入的Json 设置Friend 界面的右边的内容
    var resetElementInFriendContent_Query = function (obj) {
        clearElement_FriendContent();

        var display = $('#main_tab_friend_display').get(0);

        var oForm = document.createElement('form');

        var oAccount = document.createElement('div');
        var oEmail = document.createElement('div');
        var oMajor = document.createElement('div');
        var oDate = document.createElement('div');
        var oClass = document.createElement('div');
        oAccount.style.display = 'none';
        oEmail.style.display = 'none';
        oMajor.style.display = 'none';
        oDate.style.display = 'none';
        oClass.style.display = 'none';

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

        var arr = oForm.getElementsByTagName('div');
        for (var i = 0; i < arr.length; i++) {
            $(arr[i]).fadeIn();
        }
        display.appendChild(oForm);

        clearInterval(timer);
    };
    //已经解决的问题列表 重新依据haveSolvedList设置元素
    var resetElementInSolvedList = function () {
        var oTbody = $('#main_tab_question_tbody').get(0);
        for (var i = 0; i < haveSolvedList.length; i++) {
            var oTr = document.createElement('tr');
            var oTd_Level = document.createElement('td');
            var oTd_Number = document.createElement('td');
            var oTd_Title = document.createElement('td');
            var oTd_Date = document.createElement('td');
            oTd_Level.innerHTML = haveSolvedList[i].level;
            oTd_Number.innerHTML = haveSolvedList[i].number;
            oTd_Title.innerHTML = haveSolvedList[i].title;
            oTd_Date.innerHTML = haveSolvedList[i].date;
            oTr.appendChild(oTd_Level);
            oTr.appendChild(oTd_Number);
            oTr.appendChild(oTd_Title);
            oTr.appendChild(oTd_Date);
            oTr.onclick = function () {
                gotoQuestion(this.children[0].innerHTML, this.children[1].innerHTML);
            };
            oTbody.appendChild(oTr);
        }
    };
    //根据点击的问题的进入编码的界面 传入的分别为题的等级与编号
    var gotoQuestion = function (level, number) {
        var temp = document.createElement("form");
        temp.action = "/program/question?index=" + number + "&level=" + level;
        temp.method = "post";
        temp.style.display = "none";
        temp.submit();
    };
    //在基本信息界面的信息提示框
    var setTabInfoContent = function (str) {
        $('#main_tab_info_content').get(0).innerHTML = str;
    };
    //在详细信息界面的信息提示框
    var setTabMoreInfoContent = function (str) {
        $('#main_tab_moreinfo_content').get(0).innerHTML = str;
    };
    //同意添加好友
    var requestFriendAgree = function () {
        var oTbody = $('#main_tab_request_tbody').get(0);
        var obj = this.parentNode.parentNode.parentNode;
        targetAccount = obj.childNodes[0].innerHTML;
        $.ajax({
            url: '/setting/agreerefuse',
            type: 'POST',
            data: {
                account: account,
                targetaccount: targetAccount,
                agree: true
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    postGetFriend();
                    $(obj).fadeOut();
                    setTimeout(function () {
                        oTbody.removeChild(obj);
                    }, 1000);
                }
            }
        });
    };
    //拒绝添加好友
    var requestFriendRefuse = function () {
        var oTbody = $('#main_tab_request_tbody').get(0);
        var obj = this.parentNode.parentNode.parentNode;
        $.ajax({
            url: '/setting/agreerefuse',
            type: 'POST',
            data: {
                account: account,
                targetaccount: targetAccount,
                agree: false
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    $(obj).fadeOut();
                    setTimeout(function () {
                        oTbody.removeChild(obj);
                    }, 1000);
                }
            }
        });
    };
    //在朋友界面点击查询
    var friend_query = function () {
        //目标的Account
        targetAccount = this.parentNode.parentNode.parentNode.children[0].innerHTML;
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
                    resetElementInFriendContent_Query(json.obj);
                }
            }
        })
    };
    //在朋友界面点击删除
    var friend_delete = function () {
        //目标的Account
        var oObj = this.parentNode.parentNode.parentNode;
        targetAccount = oObj.children[0].innerHTML;
        $.ajax({
            url: "/setting/delete",
            type: "POST",
            data: {
                account: account,
                targetaccount: targetAccount
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    var jqObj = $(oObj);
                    jqObj.fadeOut();
                    setTimeout(function () {
                        $('#main_tab_friend_tbody').get(0).removeChild(oObj);
                    }, 1000);
                }
            }
        })
    };
    //根据传入的obj设置 聊天的内容. 改变chatIndex
    var refreshChatRecord = function (obj) {
        var chatBox = $('#main_tab_friend_display_chatbox').get(0);
        for (var i = 0; i < obj.length; i++) {
            var oMessage = document.createElement('div');
            oMessage.setAttribute('class', 'row');
            oMessage.style.display = 'none';
            var oBr = document.createElement('br');
            oMessage.innerHTML = "<label class='col-md-offset-1 col-xs-offset-0 col-md-2 col-xs-12' id='chat_sender'></label>\
        <label class='text-primary bg-primary col-md-offset-2 col-xs-offset-1 col-md-8 col-xs-10'\
        style='border-radius: 30px' id='chat_content'></label>";
            var oSender = oMessage.getElementsByTagName('label')[0];
            var oContent = oMessage.getElementsByTagName('label')[1];
            if (obj[i].self)
                oSender.innerHTML = account;
            else
                oSender.innerHTML = targetAccount;
            var str = obj[i].info;
            str = str.replace('\n', '<br>');
            oContent.innerHTML = str;

            chatBox.appendChild(oMessage);
            chatBox.appendChild(oBr);
        }
        var arr = chatBox.getElementsByTagName('div');
        for (var i = chatIndex; i < arr.length; i++) {
            $(arr[i]).fadeIn();
        }
        chatIndex += obj.length;
    };
    //发送消息的方法
    var sendMessage = function () {
        var oContent = $('#main_tab_friend_display_inputbox').get(0);
        var content = $('#main_tab_friend_display_inputbox').val();
        $.ajax({
            url: "/model/send",
            type: "POST",
            data: {
                account: account,
                targetaccount: targetAccount,
                chatindex: chatIndex,
                content: content
            },
            success: function (json) {
                oContent.value = "";
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    clearInterval(timer);
                    refreshChatRecord(json.obj);
                    timer = setInterval(getChat, 5000);
                }
            }
        })
    };
    //在朋友界面点击聊天后 直接设置Friend 右边界面 容纳聊天记录的主体 。
    var resetElementInFriendContent_Chat = function () {
        var oDisplay = $('#main_tab_friend_display').get(0);
        oDisplay.innerHTML = "\
            <div class='row' id='main_tab_friend_display_chatbox'>\
        </div>\
        <br>\
        <div class='row'>\
            <div class='input-group col-md-offset-1 col-md-11 col-xs-offset-0 col-xs-12'>\
            <div class='container'>\
            <div class='col-md-8 col-xs-8' style='display:none;'>\
            <textarea class='bg-info text-info' style='width: 100%; height: 100px;border-radius: 10px' id='main_tab_friend_display_inputbox'></textarea>\
            </div>\
            <div class='col-md-2 col-xs-4' style='display:none;'>\
            <img class='img-circle' src='/source/mail.svg' style='height: 100px;cursor: pointer' id='main_tab_friend_display_send' clk='4'>\
            </div>\
            </div>\
            </div>\
            </div>";

        var arr = oDisplay.getElementsByTagName('div');
        for (var i = 0; i < arr.length; i++) {
            $(arr[i]).fadeIn();
        }

        $("[clk='4']").click(function () {
            sendMessage();
        });
        timer = setInterval(getChat, 5000);
    };
    //得到自己所需要的聊天的信息
    var getChat = function () {
        $.ajax({
            url: "/model/chat",
            type: "POST",
            data: {
                account: account,
                targetaccount: targetAccount,
                chatindex: chatIndex
            },
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status) {
                    refreshChatRecord(json.obj);
                }
            }
        })
    }
    //在朋友界面点击聊天:会将chatIndex 的数值进行重置为0。
    var friend_chat = function () {
        targetAccount = this.parentNode.parentNode.parentNode.children[0].innerHTML;
        chatIndex = 0;
        clearElement_FriendContent();
        resetElementInFriendContent_Chat();
        getChat();
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

            oQuery.innerHTML = "<img src='/source/query.svg'>";
            oDelete.innerHTML = "<img src='/source/delete.svg'>";
            oChat.innerHTML = "<img src='/source/chat.svg'>";
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
    //设置朋友界面的提示信息
    var setTabFriendContent = function (infos) {
        $('#main_tab_friend_add_warning').get(0).innerHTML = infos;
    };
    //添加朋友的按钮
    var addFriend = function () {
        var targetaccount = $('#main_tab_friend_add_content').val();
        $.ajax({
            url: '/setting/add',
            type: 'POST',
            data: {
                account: account,
                targetaccount: targetaccount
            },
            success: function (json) {
                json = JSON.parse(json);
                setTabFriendContent(json.infos);
            }
        })
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
    $('#main_tab_friend_add').click(addFriend);
    setTimeout(function () {
        if (!auto_login) {
            auto_LoginFailed();
        } else {
            auto_LoginSuccess();
        }
    }, 2500);        //登陆初始调用的方法
});