$(function () {
    /**myself:
     * account  帐号
     * create   发帖数
     * follow   跟帖数
     */
    var myself;

    /**
     * element in list
     * id       帖子的编号[Hide Not Exist In View]
     * title    标题
     * owner    持有者
     */
    var list;

    var oAccount = $('#topic_account').get(0);
    var oCreate = $('#topic_create').get(0);
    var oFollow = $('#topic_follow').get(0);

    var syncView_Myself = function () {
        oAccount.innerHTML = myself.account;
        oCreate.innerHTML = myself.create;
        oFollow.innerHTML = myself.follow;
    };

    var readData_Myself = function () {
        $.ajax({
            url: "/model/topicmyself",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                if (json.status) {
                    myself = json.obj;
                    syncView_Myself();
                }
            }
        })

    };


    var Initialization = function () {
        setTimeout(function () {
            if (!auto_login) {
                window.location = "/";
                alert("请线登陆");
            }
            $('#background').fadeIn();
            readData_Myself();
        },2500);
    };
    Initialization();
});