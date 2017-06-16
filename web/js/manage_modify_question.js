$(function () {
    /**
     * 需要被改变的user
     * 属性有:
     * id           id
     * lv           等级
     * number       编号
     * title        标题
     * content      内容
     * tip          提示
     */
    var question = json;

    /**
     * 几个视图对象
     * @type {*}
     */
    var oId = $('#modify_question_id').get(0);
    var oLv = $('#modify_question_lv').get(0);
    var oNumber = $('#modify_question_number').get(0);
    var oTitle = $('#modify_question_title').get(0);
    var oContent = $('#modify_question_content').get(0);
    var oTip = $('#modify_question_tip').get(0);

    var syncView = function () {
        oId.value = question.id;
        oLv.value = question.lv;
        oNumber.value = question.number;
        oTitle.value = question.title;
        oContent.value = question.content;
        oTip.value = question.tip;
    };
    var syncData = function () {
        question.id = oId.value;
        question.lv = oLv.value;
        question.number = oNumber.value;
        question.title = oTitle.value;
        question.content = oContent.value;
        question.tip = oTip.value;
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
            url: "/managecontroll/changequestion",
            type: "POST",
            data: question,
            success: function (json) {
                json = JSON.parse(json);
                setInfo(json.infos);
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