/**
 * Created by FuFangzhou on 2017/6/14.
 */
$(function () {
    //number title tip content
    var questionContent = json.obj;
    $('#question_title').get(0).innerHTML = questionContent.number + '.' + questionContent.title;
    $('#question_content').get(0).innerHTML = questionContent.content;
    $('#question_tip').get(0).innerHTML = questionContent.tip;
    var setInfo = function (str) {
        $('#question_info').fadeIn();
        $('#question_info').get(0).innerHTML = str;
    };
    $('#question_submit').click(function () {
        $('#question_info').css("display","none");
        setInfo("正在提交...");
        $.ajax({
            url: "/program/submit",
            type: "POST",
            data: {
                level:questionContent.level,
                number:questionContent.number,
                content: $('#question_submit_content').val()
            },
            success: function (json) {
                json = JSON.parse(json);
                setInfo(json.infos);
            }
        })
    });
});