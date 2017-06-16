$(function () {
    /**
     * 需要被改变的user
     * 属性有:
     * account      账号
     * clazz        班级
     * date         日期
     * email        邮箱
     * id           id
     * password     密码
     */
    var oUser = json;

    /**
     * 几个视图对象
     * @type {*}
     */
    var oAccount = $('#modify_user_account').get(0);
    var oClass = $('#modify_user_class').get(0);
    var oDate = $('#modify_user_date').get(0);
    var oEmail = $('#modify_user_email').get(0);
    var oId = $('#modify_user_id').get(0);
    var oPassword = $('#modify_user_password').get(0);

    var syncView = function () {
        oAccount.value = oUser.account;
        oClass.value = oUser.clazz;
        oDate.value = oUser.date;
        oEmail.value = oUser.email;
        oId.value = oUser.id;
        oPassword.value = oUser.password;
    };
    var syncData = function () {
        oUser.account = oAccount.value;
        oUser.clazz = oClass.value;
        oUser.date = oDate.value;
        oUser.email = oEmail.value;
        oUser.id = oId.value;
        oUser.password = oPassword.value;
    };

    var setInfo = function (str) {
        $('#modify_warning').css('display', 'none');
        var obj = $('#modify_warning').get(0);
        if (str != "") {
            obj.innerHTML = str;
            $('#modify_warning').fadeIn();
        }
    }

    var reset = function () {
        syncView();
    };
    var commit = function () {
        syncData();
        $.ajax({
            url: "/managecontroll/changeuser",
            type: "POST",
            data: oUser,
            success: function (json) {
                json = JSON.parse(json);
                setInfo(json.infos);
            }
        })
    }

    var Initialization = function () {
        syncView();
        $('#modify_user_commit').click(commit);
        $('#modify_user_reset').click(reset);
    };
    Initialization();
});