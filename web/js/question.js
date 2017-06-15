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
        $('#question_info').get(0).innerHTML = str;
    };
    $('#question_submit').click(function () {
        $.ajax({
            url: "/program/submit",
            type: "POST",
            data: {
                content: $('#question_submit_content').val()
            },
            success: function (json) {
                setInfo(json.infos);
            }
        })
    });
});