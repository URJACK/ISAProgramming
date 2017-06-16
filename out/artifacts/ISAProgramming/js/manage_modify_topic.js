$(function () {
    /**
     * 需要被改变的user
     * 属性有:
     * id           id
     * uid          拥有者的编号
     * title        标题
     * content      内容
     * owner        拥有者的名字
     */
    var topic = json;

    /**
     * 几个视图对象
     * @type {*}
     */
    var oId = $('#modify_topic_id').get(0);
    var oUid = $('#modify_topic_uid').get(0);
    var oTitle = $('#modify_topic_title').get(0);
    var oContent = $('#modify_topic_content').get(0);
    var oOwner = $('#modify_topic_owner').get(0);

    var syncView = function () {
        oId.value = topic.id;
        oUid.value = topic.uid;
        oTitle.value = topic.title;
        oContent.value = topic.content;
        oOwner.value = topic.owner;
    };
    var syncData = function () {
        topic.id = oId.value;
        topic.uid = oUid.value;
        topic.title = oTitle.value;
        topic.content = oContent.value;
        topic.owner = oOwner.value;
    };

    var setInfo = function (str) {
        $('#modify_warning').css('display', 'none');
        var obj = $('#modify_warning').get(0);
        if (str != "") {
            obj.innerHTML = str;
            $('#modify_warning').fadeIn();
        }
    };

    var reset = function () {
        syncView();
    };
    var commit = function () {
        syncData();
        $.ajax({
            url: "/managecontroll/changetopic",
            type: "POST",
            data: topic,
            success: function (json) {
                json = JSON.parse(json);
                setInfo(json.infos);
                if (json.status) {
                    var obj = JSON.parse(json.obj);
                    topic.owner = obj.owner;
                    syncView();
                }
            }
        })
    };

    var Initialization = function () {
        syncView();
        $('#modify_commit').click(commit);
        $('#modify_reset').click(reset);
    };
    Initialization();
});