/**
 * Created by FuFangzhou on 2017/6/5.
 */
$(function () {
    var auto_LoginFailed = function () {
        alert("你的账户信息不符合");
        window.location = "/home";
    };
    var auto_LoginSuccess = function () {

    };
    setTimeout(function () {
        if (!auto_login) {
            auto_LoginFailed();
        } else {
            auto_LoginSuccess();
        }
    },3000);
});