$(function () {
    var userList;
    var questionList;
    var topicList;
    var matchList;
    var readData_User = function () {
        $.ajax({
            url: "/manage/user",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status)
                    userList = json.obj;
            }
        })
    };
    var readData_Question = function () {
        $.ajax({
            url: "/manage/question",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status)
                    questionList = json.obj;
            }
        })
    };
    var readData_Topic = function () {
        $.ajax({
            url: "/manage/topic",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status)
                    topicList = json.obj;
            }
        })
    };
    var readData_Match = function () {
        $.ajax({
            url: "/manage/match",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                console.log(json.infos);
                if (json.status)
                    matchList = json.obj;
            }
        })
    };
    var refreshData_User = function () {

    };
    var refreshData_Question = function () {

    };
    var refreshData_Topic = function () {

    };
    var refreshData_Match = function () {

    };
    var Initilization = function () {
        readData_User();
        readData_Question();
        readData_Topic();
        readData_Match();
        refreshData_User();
        refreshData_Question();
        refreshData_Topic();
        refreshData_Match();
    };
    //初始化调用
    Initilization();
});