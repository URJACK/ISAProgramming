$(function () {
    var userList;
    var questionList;
    var topicList;
    var matchList;

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

        oOperate_Div.appendChild(oA_delete);
        oOperate_Div.appendChild(oA_modify);

        oOperate_Div.setAttribute('class', 'btn-group');

        oTd_Operate.appendChild(oOperate_Div);
        return oTd_Operate;
    }

    var readData_User = function () {
        $.ajax({
            url: "/manage/user",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                userList = json;
                refreshData_User();
            }
        })
    };
    var readData_Question = function () {
        $.ajax({
            url: "/manage/question",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                questionList = json;
                refreshData_Question();
            }
        })
    };
    var readData_Topic = function () {
        $.ajax({
            url: "/manage/topic",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                topicList = json;
                refreshData_Topic();
            }
        })
    };
    var readData_Match = function () {
        $.ajax({
            url: "/manage/match",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                matchList = json;
                refreshData_Match();
            }
        })
    };
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

    };
    var refreshData_Match = function () {

    };
    var Initilization = function () {
        readData_User();
        readData_Question();
        // readData_Topic();
        // readData_Match();
    };
    //初始化调用
    Initilization();
});