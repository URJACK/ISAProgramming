$(function () {
    //当前选择的列表的游标 0 ~ 3
    var tabCursor = 0;
    //用户的列表
    var userList;
    //题库的列表
    var questionList;
    //帖子的列表
    var topicList;
    //比赛的列表
    var matchList;

    /**
     * 每一个列表的页码:会因为搜索等操作而遭到重置
     */
    var userPosition = 0;
    var questionPosition = 0;
    var topicPosition = 0;
    var matchPosition = 0;

    /**
     * 每一个列表的搜索值:会因为一次点击事件而缓存在这里，并且会进行一次回显
     */
    var userSearchBuffer;
    var questionSearchBuffer;
    var topicSearchBuffer;
    var matchSearchBuffer;

    /**
     * tabCursor:的数值对应列表
     * tabCursor:0 user
     * tabCursor:1 question
     * tabCursor:2 topic
     * tabCursor:3 match
     */
    var deleteOperate = function (id) {
        if (tabCursor == 0) {
            deleteUser(id);
        } else if (tabCursor == 1) {
            deleteQuestion(id);
        } else if (tabCursor == 2) {
            deleteTopic(id);
        } else if (tabCursor == 3) {
            deleteMatch(id);
        }
    };
    var modifyOperate = function (id) {
        if (tabCursor == 0) {
            modifyUser(id);
        } else if (tabCursor == 1) {
            modifyQuestion(id);
        } else if (tabCursor == 2) {
            modifyTopic(id);
        } else if (tabCursor == 3) {
            modifyMatch(id);
        }
    };

    /**
     * 删除某一项数据
     * @param id 该项数据的id
     */
    var deleteUser = function (id) {
        console.log("delete User " + id);
    };
    var deleteQuestion = function (id) {
        console.log("delete Question " + id);
    };
    var deleteTopic = function (id) {
        console.log("delete Topic " + id);
    };
    var deleteMatch = function (id) {

    };

    /**
     * 修改某一项数据
     * @param id 该项数据的id
     */
    var modifyUser = function (id) {
        var temp = document.createElement("form");
        temp.action = "/managecontroll/user?id=" + id;
        temp.method = "post";
        temp.style.display = "none";
        temp.submit();
    };
    var modifyQuestion = function (id) {
        var temp = document.createElement("form");
        temp.action = "/managecontroll/question?id=" + id;
        temp.method = "post";
        temp.style.display = "none";
        temp.submit();
    };
    var modifyTopic = function (id) {
        var temp = document.createElement("form");
        temp.action = "/managecontroll/topic?id=" + id;
        temp.method = "post";
        temp.style.display = "none";
        temp.submit();
    };
    var modifyMatch = function (id) {

    };

    /**
     * 得到Operate元素
     * @returns {Element}
     */
    function getOperate() {
        var oTd_Operate = document.createElement('td');

        var oOperate_Div = document.createElement('div');

        var oA_delete = document.createElement('a');
        var oA_modify = document.createElement('a');

        var oImg_delete = document.createElement('img');
        var oImg_modify = document.createElement('img');

        oImg_delete.src = "/source/delete.svg";
        oImg_modify.src = "/source/query.svg";

        oA_delete.appendChild(oImg_delete);
        oA_modify.appendChild(oImg_modify);

        oA_delete.setAttribute('class', 'btn btn-default');
        oA_modify.setAttribute('class', 'btn btn-default');

        oA_delete.onclick = function () {
            var id = this.parentNode.parentNode.parentNode.children[0].innerHTML;
            deleteOperate(id);
        };
        oA_modify.onclick = function () {
            var id = this.parentNode.parentNode.parentNode.children[0].innerHTML;
            modifyOperate(id);
        };

        oOperate_Div.appendChild(oA_delete);
        oOperate_Div.appendChild(oA_modify);

        oOperate_Div.setAttribute('class', 'btn-group');

        oTd_Operate.appendChild(oOperate_Div);
        return oTd_Operate;
    }

    /**
     * 从服务器读取数据，并调用RefreshData 方法
     */
    var readData_User = function (position) {
        $.ajax({
            url: "/manage/user",
            type: "POST",
            data: {
                position: position
            },
            success: function (json) {
                json = JSON.parse(json);
                if (json.status) {
                    userList = json.obj;
                    refreshData_User();
                } else {
                    setTips(json.infos);
                    if (userPosition < 0)
                        userPosition++;
                    else
                        userPosition--;
                }
            }
        })
    };
    var readData_Question = function (position) {
        $.ajax({
            url: "/manage/question",
            type: "POST",
            data: {
                position: position
            },
            success: function (json) {
                json = JSON.parse(json);
                if (json.status) {
                    questionList = json.obj;
                    refreshData_Question();
                } else {
                    setTips(json.infos);
                    if (questionPosition < 0)
                        questionPosition++;
                    else
                        questionPosition--;
                }
            }
        })
    };
    var readData_Topic = function (position) {
        $.ajax({
            url: "/manage/topic",
            type: "POST",
            data: {
                position: position
            },
            success: function (json) {
                json = JSON.parse(json);
                if (json.status) {
                    topicList = json.obj;
                    refreshData_Topic();
                } else {
                    setTips(json.infos);
                    if (topicPosition < 0)
                        topicPosition++;
                    else
                        topicPosition--;
                }
            }
        })
    };
    var readData_Match = function (position) {
        $.ajax({
            url: "/manage/match",
            type: "POST",
            data: {
                position: position
            },
            success: function (json) {
                json = JSON.parse(json);
                matchList = json;
                refreshData_Match();
            }
        })
    };

    /**
     * 根据该页的List来刷新视图
     */
    var refreshData_User = function () {
        var oTbody = $('#manage_user_tbody').get(0);
        oTbody.innerHTML = "";
        for (var i = 0; i < userList.length; i++) {
            var oTr = document.createElement('tr');

            var oTd_Id = document.createElement('td');
            var oTd_Account = document.createElement('td');
            var oTd_Email = document.createElement('td');
            var oTd_Operate = getOperate();

            oTd_Id.innerHTML = userList[i].id;
            oTd_Account.innerHTML = userList[i].account;
            oTd_Email.innerHTML = userList[i].email;

            oTr.appendChild(oTd_Id);
            oTr.appendChild(oTd_Account);
            oTr.appendChild(oTd_Email);
            oTr.appendChild(oTd_Operate);

            oTbody.appendChild(oTr);
        }
    };
    var refreshData_Question = function () {

        var oTbody = $('#manage_question_tbody').get(0);
        oTbody.innerHTML = "";
        for (var i = 0; i < questionList.length; i++) {
            var oTr = document.createElement('tr');

            var oTd_Id = document.createElement('td');
            var oTd_Level = document.createElement('td');
            var oTd_Number = document.createElement('td');
            var oTd_Title = document.createElement('td');
            var oTd_Operate = getOperate();


            oTd_Id.innerHTML = questionList[i].id;
            oTd_Level.innerHTML = questionList[i].level;
            oTd_Number.innerHTML = questionList[i].number;
            oTd_Title.innerHTML = questionList[i].title;

            oTr.appendChild(oTd_Id);
            oTr.appendChild(oTd_Level);
            oTr.appendChild(oTd_Number);
            oTr.appendChild(oTd_Title);
            oTr.appendChild(oTd_Operate);

            oTbody.appendChild(oTr);
        }
    };
    var refreshData_Topic = function () {
        var oTbody = $('#manage_topic_tbody').get(0);
        oTbody.innerHTML = "";
        for (var i = 0; i < topicList.length; i++) {
            var oTr = document.createElement('tr');

            var oTd_Id = document.createElement('td');
            var oTd_Title = document.createElement('td');
            var oTd_Owner = document.createElement('td');
            var oTd_Operate = getOperate();

            oTd_Id.innerHTML = topicList[i].id;
            oTd_Title.innerHTML = topicList[i].title;
            oTd_Owner.innerHTML = topicList[i].owner;

            oTr.appendChild(oTd_Id);
            oTr.appendChild(oTd_Title);
            oTr.appendChild(oTd_Owner);
            oTr.appendChild(oTd_Operate);

            oTbody.appendChild(oTr);
        }
    };
    var refreshData_Match = function () {

    };

    /**
     * 给上下页码设置翻页监听
     */
    var setTurnPageListener = function () {
        var turnUpPageUser = function () {
            setTips("");
            userPosition--;
            readData_User(userPosition);
        };
        var turnDownPageUser = function () {
            setTips("");
            userPosition++;
            readData_User(userPosition);
        };
        var turnUpPageQuestion = function () {
            setTips("");
            questionPosition--;
            readData_Question(questionPosition);
        };
        var turnDownPageQuestion = function () {
            setTips("");
            questionPosition++;
            readData_Question(questionPosition);
        };
        var turnUpPageTopic = function () {
            setTips("");
            topicPosition--;
            readData_Topic(topicPosition);
        };
        var turnDownPageTopic = function () {
            setTips("");
            topicPosition++;
            readData_Topic(topicPosition);
        };
        var turnUpPageMatch = function () {
            console.log("Match 上");
        };
        var turnDownPageMatch = function () {
            console.log("Match 下");
        };

        $('#manage_user_uppage').click(turnUpPageUser);
        $('#manage_user_downpage').click(turnDownPageUser);
        $('#manage_question_uppage').click(turnUpPageQuestion);
        $('#manage_question_downpage').click(turnDownPageQuestion);
        $('#manage_topic_uppage').click(turnUpPageTopic);
        $('#manage_topic_downpage').click(turnDownPageTopic);
        $('#manage_match_uppage').click(turnUpPageMatch);
        $('#manage_match_downpage').click(turnDownPageMatch);

    };

    /**
     * 在提示框里设置信息
     */
    var setTips = function (infos) {
        $('#manage_warning').css('display', 'none');
        if (infos != "") {
            $('#manage_warning').get(0).innerHTML = infos;
            $('#manage_warning').fadeIn();
        }
    };

    /**
     * 初始化调用
     */
    var Initilization = function () {
        setTimeout(function () {
            if (!auto_login) {
                window.location = "/";
                alert("你的身份信息不符合")
            }
            $('#manage_to_user').click(function () {
                setTips("");
                tabCursor = 0;
            });
            $('#manage_to_question').click(function () {
                setTips("");
                tabCursor = 1;
            });
            $('#manage_to_topic').click(function () {
                setTips("");
                tabCursor = 2;
            });
            $('#manage_to_match').click(function () {
                setTips("");
                tabCursor = 3;
            });

            setTurnPageListener();

            readData_User(0);
            readData_Question(0);
            readData_Topic(0);
            // readData_Match();
        }, 2500);
    };
    Initilization();
});