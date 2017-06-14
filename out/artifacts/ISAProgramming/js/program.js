/**
 * Created by FuFangzhou on 2017/6/14.
 */
$(function () {
    //当前问题的集合
    var questionList = null;
    //问题列表的集合的框架
    var content_questionset = "\
        <div class='panel panel-primary'>\
    <div class='panel-heading'>\
    <h3 class='panel-title'>Basic</h3>\
    </div>\
    <div class='panel-body' id='select_questionset_1'>\
    <div class='row'>\
    <img class='col-md-4' src='/source/Question_Lv1.svg' alt=''>\
    <label class='col-md-8 text-primary'>初级</label>\
    </div>\
    </div>\
    </div>\
    <div class='panel panel-primary'>\
    <div class='panel-heading'>\
    <h3 class='panel-title'>Skilled</h3>\
    </div>\
    <div class='panel-body' id='select_questionset_2'>\
    <div class='row'>\
    <img class='col-md-4' src='/source/Question_Lv2.svg' alt=''>\
    <label class='col-md-8 text-primary'>中级</label>\
    </div>\
    </div>\
    </div>\
    <div class='panel panel-primary'>\
    <div class='panel-heading'>\
    <h3 class='panel-title'>Proficient</h3>\
    </div>\
    <div class='panel-body' id='select_questionset_3'>\
    <div class='row'>\
    <img class='col-md-4' src='/source/Question_Lv3.svg' alt=''>\
    <label class='col-md-8 text-primary'>高级</label>\
    </div>\
    </div>\
    </div>";
    //问题列表的框架
    var content_question = "<table class='table table-bordered'>\
    <caption><label class='text-info' id='questionset_name'>初级测试题</label></caption>\
        <thead>\
        <tr>\
        <th>标号</th>\
        <th>标题</th>\
        <th>通过</th>\
        <th>提交</th>\
        <th>通过率</th>\
        </tr>\
        </thead>\
        <tbody id='question_content'>\
        </tbody>\
        </table>";
    //根据QuestionList 给实际的容器设置内容
    var putElementByQuestionList = function (questionListJson) {

    };
    //在改变内容后，给内容重新设置监听等动作
    var setClickListener = function (index) {
        if (index == 0) {
            function getList(target) {
                $.ajax({
                    url: "/program/set",
                    type: "POST",
                    data: {
                        target: target
                    },
                    success: function (json) {
                        json = JSON.parse(json);
                        console.log(json.infos);
                        if (json.status) {
                            questionList = json.obj;
                        }
                    }
                })
            };
            $('#select_questionset_1').click(function () {
                getList(1);
            });
            $('#select_questionset_2').click(function () {
                getList(2);
            });
            $('#select_questionset_3').click(function () {
                getList(3);
            });
        }
    };
    var changeContentFlag = true;
    //改变内容的方法,正在改变的时候flag 为 true : 即禁止再次改变的动作
    var changeContent = function (index) {
        if (changeContentFlag) {
            changeContentFlag = false;
            $('#select_content').fadeOut(800);
            setTimeout(function () {
                if (index == 0) {
                    $('#select_content').get(0).innerHTML = content_questionset;
                } else if (index == 1) {
                    $('#select_content').get(0).innerHTML = content_question;
                    putElementByQuestionList(questionList);
                }
                $('#select_content').fadeIn(500);
                setClickListener(index);
                setTimeout(function () {
                    changeContentFlag = true;
                }, 500)
            }, 1000);
        }
    };
    $('#select_questionset').click(function () {
        changeContent(0);
    });
    $('#select_question').click(function () {
        changeContent(1);
    });
});