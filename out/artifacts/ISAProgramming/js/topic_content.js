$(function () {
    /**topic:
     * id       帖子的id[不显示]
     * title    标题
     * owner    作者
     * content  内容
     */
    var topic = json;

    var oTitle = $('#topic_content_title').get(0);
    var oOwner = $('#topic_content_owner').get(0);
    var oContent = $('#topic_content_content').get(0);

    var syncView = function () {
        oTitle.innerHTML = topic.title;
        oContent.innerHTML = topic.content;
        oOwner.innerHTML = topic.owner;
    };

    var Initialization = function () {
        setTimeout(function () {
            if (!auto_login) {
                window.location = "/";
                alert("请线登陆");
            }
            syncView();
            $('#background').fadeIn();
        }, 2500);
    };
    Initialization();
});